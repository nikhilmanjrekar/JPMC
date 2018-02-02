# JPMC

Solution:
I have divided the requirement into several steps (Pseudo code):

Message parsing to create Products pojo object.
Maintain a hash map of consolidatedProduct pojo objects which holds totals where key is product type.
If product operation is specified, perform adjustment and create adjustedProduct pojo. Add this pojo to the adjustedProducts list. Also add adjustment changes to consolidatedProduct pojo.
If product operation is not specified, simply add current product details to consolidatedProduct pojo.
Add consolidatedProduct pojo to a list for reporting purpose.
After adding every 10th record to this list, print the contents of all the consolidatedProduct pojos as Sales Report.
After reading 50 records, print the contents of adjustedProduct pojos from adjustedProducts list as Adjustment Report.
 
Assumptions:
Sales records are received as rows in the text file on a file system.
Currently I have considered products as cold drink brands.
After printing Sales Report for 10 records, application hauls for 1000 ms. This is just to give a feel of real time message processing.
 
Notes:
Please execute the code using MessageProcessor.java.
