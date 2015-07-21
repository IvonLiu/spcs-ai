% Hello world!
fprintf('Hello world!\n');

% Read the file
%[data,varnames,casenames] = tblread('training_set.tab', '\t');

scores = zeros(1,length(casenames));

for row=1:length(casenames)
    
    positive_sum = 0;
    positive_count = 0;

    negative_sum = 0;
    negative_count = 0;
    
    for col=1:length(varnames)
        if strcmp(varnames(col,6), '1')
            % is positive
            positive_count = positive_count + 1;
            positive_sum = positive_sum + data(row,col);
        else
            % is negative
            negative_count = negative_count + 1;
            negative_sum = negative_sum + data(row,col);
        end    
    end
    
    positive_avg = positive_sum / positive_count;
    negative_avg = negative_sum / negative_count;
    %fprintf('Positive average=%f, negative average=%f\n', positive_avg, negative_avg);
    
    positive_variance_sum = 0;
    negative_variance_sum = 0;
    
    for col=1:length(varnames)
        if strcmp(varnames(col,6), '1')
            % is positive
            variance = (data(row,col)-positive_avg)*(data(row,col)-positive_avg);
            positive_variance_sum = positive_variance_sum + variance;
        else
            % is negative
            variance = (data(row,col)-negative_avg)*(data(row,col)-negative_avg);
            negative_variance_sum = negative_variance_sum + variance;
        end    
    end
    
    positive_variance_avg = positive_variance_sum / positive_count;
    negative_variance_avg = negative_variance_sum / negative_count;
    %fprintf('Positive variance average=%f, negative variance average=%f\n', positive_variance_avg, negative_variance_avg);
        
    score = (positive_avg - negative_avg) / (positive_variance_avg + negative_variance_avg);
    scores(row) = score;
    %fprintf('Score: %f\n', score);
    
end

scores = sort(scores);

for i=1:length(scores)
    fprintf('Score %d: %f\n', i, scores(i));
end
fprintf('Done\n');

