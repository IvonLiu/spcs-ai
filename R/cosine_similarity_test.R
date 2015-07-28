#read in all the input data
cpdtr <- read.csv("data/coupon_detail_train.csv")
cpltr <- read.csv("data/coupon_list_train.csv")
cplte <- read.csv("data/coupon_list_test.csv")
ulist <- read.csv("data/user_list.csv")

#making of the train set

# copies the rest of the coupon data for each coupon that was purchased
train <- merge(cpdtr,cpltr)

# only keep select columns (reduce 29 columns into 15 columns)
train <- train[,c("COUPON_ID_hash","USER_ID_hash",
                  "GENRE_NAME","DISCOUNT_PRICE",
                  "USABLE_DATE_MON","USABLE_DATE_TUE","USABLE_DATE_WED","USABLE_DATE_THU",
                  "USABLE_DATE_FRI","USABLE_DATE_SAT","USABLE_DATE_SUN","USABLE_DATE_HOLIDAY",
                  "USABLE_DATE_BEFORE_HOLIDAY","ken_name","small_area_name")]

#combine the test set with the train

# add USER_ID_hash column to cplte
cplte$USER_ID_hash <- "dummyuser"

# only keep select columns (reduce 25 columns into 15 columns)
cpchar <- cplte[,c("COUPON_ID_hash","USER_ID_hash",
                   "GENRE_NAME","DISCOUNT_PRICE",
                   "USABLE_DATE_MON","USABLE_DATE_TUE","USABLE_DATE_WED","USABLE_DATE_THU",
                   "USABLE_DATE_FRI","USABLE_DATE_SAT","USABLE_DATE_SUN","USABLE_DATE_HOLIDAY",
                   "USABLE_DATE_BEFORE_HOLIDAY","ken_name","small_area_name")]

# combine the training and test coupons together
train <- rbind(train,cpchar)

#NA imputation

# replace all 'NA' values with 1
train[is.na(train)] <- 1

#feature engineering (binning the price into different buckets)
train$DISCOUNT_PRICE <- cut(train$DISCOUNT_PRICE,breaks=c(-0.01,0,1000,10000,50000,100000,Inf),labels=c("free","cheap","moderate","expensive","high","luxury"))

#convert the factors to columns of 0's and 1's
test_a <- train[,c(1,2)]
test_c <- train[,-c(1,2)]
test_b <- model.matrix(~ -1 + .,train[,-c(1,2)])
test_b <- test_b[1:10]

train <- cbind(train[,c(1,2)],model.matrix(~ -1 + .,train[,-c(1,2)]))

#separate the test from train
test <- train[train$USER_ID_hash=="dummyuser",]
# delete column 2 (USER_ID_hash) because it is all dummyuser
test <- test[,-2]
train <- train[train$USER_ID_hash!="dummyuser",]

#data frame of user characteristics
# take mean of each column for each user
uchar <- aggregate(.~USER_ID_hash, data=train[,-1],FUN=mean)

user_matrix <- as.matrix(uchar[,2:ncol(uchar)])
cp_matrix <- as.matrix(test[,2:ncol(test)])
tcp_matrix <- t(cp_matrix)

#calculation of cosine similairties of users and coupons
score = as.matrix(uchar[,2:ncol(uchar)]) %*% t(as.matrix(test[,2:ncol(test)]))
#order the list of coupons according to similairties and take only first 10 coupons
uchar$PURCHASED_COUPONS <- do.call(rbind, lapply(1:nrow(uchar),FUN=function(i){
  purchased_cp <- paste(test$COUPON_ID_hash[order(score[i,], decreasing = TRUE)][1:10],collapse=" ")
  return(purchased_cp)
}))

#make submission
uchar <- merge(ulist, uchar, all.x=TRUE)
submission <- uchar[,c("USER_ID_hash","PURCHASED_COUPONS")]
write.csv(submission, file="data/output/cosine_sim.csv", row.names=FALSE)