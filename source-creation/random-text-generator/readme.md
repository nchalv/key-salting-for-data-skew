## Random Skewed Text Generator

This small **python3** project's goal is to create a random text where one
word appears significantly more often than most others. Each line of the text
contains a *pre-defined number of words, **w**,* and the text as a whole consists
of a *pre-defined number of lines, **l***.

### The algorithm
To create every line of the text, we select a specific word which will act as
the **hot key**, e.g. *pepega*. We perform ***w*** *Bernoulli trials* with a
probability of success set at *p*. In case of success, we insert the selected
word, in our case *pepega*, in the text. Otherwise, we insert a randomly selected
word.

We have used a thesaurus of English words available here:
 [https://github.com/dwyl/english-words](https://github.com/dwyl/english-words).

We repeat the procedure described above ***l*** times, (once for each line in our
text).

### Running the generator

To run the random skewed text generator type the following:

`python3 random_text_gen.py thesaurus-path output-path  l w pepega p`.

### Creating the stream on Kafka

The output can be utilized with *Apache Kafka*'s built-in resources to create a
stream, each record of which consists of a single line of the produced text
document. To create the stream use the following on a machine where *Appache Kafka*
is installed and running:

`bin/kafka-producer-perf-test.sh --num-records 100000 --topic test-topic --throughput 10 --payload-file ~/benchmark.txt  --producer-props acks=1 bootstrap.servers=localhost:9092,localhost:9093`
