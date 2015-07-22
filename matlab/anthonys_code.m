[data,varnames,casenames] = tblread('training_set.tab', '\t');

healthy = data(:,varnames(:,6) == '1');
unhealthy = data(:,varnames(:,6) == '0');

scores(1:4400,1:2) = 0;

for i=1:length(casenames)
    m1 = mean2(var(healthy(i,:)));
    m2 = mean2(var(unhealthy(i,:)));
    %fprintf('Positive average=%f, negative average=%f\n', m1, m2);
    
    v1 = mean2(var(healthy(i,:)));
    v2 = mean2(var(unhealthy(i,:)));
    %fprintf('Positive variance average=%f, negative variance average=%f\n', v1, v2);
    
    score = (m1 - m2) / (v1 + v2);
    scores(i, 1) = i;
    scores(i, 2) = score;
end

scores = sort(scores);

for i = 1:50
   fprintf('Score %d: %f\n', scores(i,1), scores(i,2));
end
fprintf('Done\n');