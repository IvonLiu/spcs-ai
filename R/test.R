n = c("ee", "ff", "gg", "hh") 
s = c("aa", "bb", "cc", "dd") 
b = c(TRUE, FALSE, TRUE, FALSE)
a = c(FALSE, TRUE, FALSE, TRUE)
df = data.frame(n, s, b, a) 

test_b2 <- model.matrix(~.,df)
test_b3 <- model.matrix(~ -1 +.,df)
