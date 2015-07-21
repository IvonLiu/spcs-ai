function [rowStr] = printCasename(charMatrix, row)
    rowStr = '';
    [length,width] = size(charMatrix);
    for i=1:width
        rowStr = strcat(rowStr,charMatrix(row,i));
    end
end