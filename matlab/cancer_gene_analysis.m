% Hello world!
fprintf('Hello world!\n');

% Read the file
%[data,varnames,casenames] = tblread('training_set.tab', '\t');

scores(1:4400,1:2) = 0;

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
    scores(row, 1) = row;
    scores(row, 2) = score;
    %fprintf('Score: %f\n', score);
    
end

scores = sortrows(scores, 2);

fprintf('----- Lowest 50 scores -----\n');
for i=1:50
    gene_name = printCasename(casenames,scores(i,1));
    fprintf('%-35s: %f\n', gene_name, scores(i,2));
end

fprintf('\n----- Top 50 scores -----\n');
for i=(length(scores)-50):length(scores)
    gene_name = printCasename(casenames,scores(i,1));
    fprintf('%-35s: %f\n', gene_name, scores(i,2));
end

fprintf('Done\n');
