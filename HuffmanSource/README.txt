BENCHMARK RESULTS:

CALGARY:

paper6 from      38105 to        25057 in        0.056
paper1 from      53161 to        34371 in        0.043
obj1 from        21504 to        17085 in        0.022
geo from         102400 to       73592 in        0.085
progc from       39611 to        26948 in        0.036
progl from       71646 to        44017 in        0.050
book1 from       768771 to       439409 in       0.500
progp from       49379 to        31248 in        0.048
pic from         513216 to       107586 in       0.123
news from        377109 to       247428 in       0.279
paper4 from      13286 to        8894 in         0.018
bib from         111261 to       73795 in        0.076
paper3 from      46526 to        28309 in        0.040
paper2 from      82199 to        48649 in        0.061
paper5 from      11954 to        8465 in         0.012
obj2 from        246814 to       195131 in       0.267
trans from       93695 to        66252 in        0.102
book2 from       610856 to       369335 in       0.709
--------
total bytes read: 3251493
total compressed bytes 1845571
total percent compression 43.239
compression time: 2.527

WATERLOO:

sail.tif from    1179784 to      1085501 in      1.195
monarch.tif from         1179784 to      1109973 in      1.192
clegg.tif from   2149096 to      2034595 in      2.106
lena.tif from    786568 to       766146 in       0.789
serrano.tif from         1498414 to      1127645 in      1.210
peppers.tif from         786568 to       756968 in       0.814
tulips.tif from  1179784 to      1135861 in      1.286
frymire.tif from         3706306 to      2188593 in      2.957
--------
total bytes read: 12466304
total compressed bytes 10205282
total percent compression 18.137
compression time: 11.549

BOOKSANDHTML:

melville.txt from        82140 to        47364 in        0.084
A7_Recursion.html from   41163 to        26189 in        0.039
jnglb10.txt from         292059 to       168618 in       0.213
ThroughTheLookingGlass.txt from  188199 to       110293 in       0.127
syllabus.htm from        33273 to        21342 in        0.024
revDictionary.txt from   1130523 to      611618 in       0.703
CiaFactBook2000.txt from         3497369 to      2260664 in      2.544
kjv10.txt from   4345020 to      2489768 in      3.035
rawMovieGross.txt from   117272 to       53833 in        0.063
quotes.htm from  61563 to        38423 in        0.045
--------
total bytes read: 9788581
total compressed bytes 5828112
total percent compression 40.460
compression time: 6.877

Question 1: What kinds of files lead to lots of compression? Why?

Answer: Text files compress the best, with Calgary hitting 43.2% compression overall. The reason text files 
work so well is because the majority of the content is just a small set of chars (alphabet + numbers + ...) used
multiple times, so there are common letters or items in that set that come up multiple times in the files. 
Huffman can give them short codes because of this and save a lot of space on each char. 

Question 2: What kinds of files saw little to no compression? Why?

Answer: The Waterloo images compress the worst, with only 18.1% compression overall. Huffman works by giving short codes
to values that appear more frequent and longer codes to rare values. The algorithm only saves space when you have 
values that show up more often then others, like in english sentences. However, since images usually have 
a larger spread of values since a normal picture contains a lot of different shades, it causes the Huffman Algorithm 
to give similar length codes to everything, causing the compressed size to only decrease a little from the original size.
On top of this the added size of the header reduces the bits saved when compressed, resulting in an even smaller delta. 

Question 3: The benchmarking program skips files with .hf suffixes (as in, it does not compress
already compressed files). Edit the code to remove this restriction. What happens when
you try to compress a .hf file that has already been compressed? Why?

Answer: Most of the files end up becoming bigger 
(ex. obj1 went from 17085 to 17690, trans went from 66252 to 66452, etc). 
A .hf file is typically as compressed as Huffman can make it, so when we try and compress it again, 
the algorithm doesn't do much since we can't exploit variable bit assignment much more if any. 
On top of this, we have the magic number, header, and all the tree data that adds extra bits to the "compressed" file.
This takes up any space that the algorithm saved,
causing the final "compressed" file size to be bigger than the initial compressed file size.