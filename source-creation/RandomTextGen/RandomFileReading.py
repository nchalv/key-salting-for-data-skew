import random
import sys
import numpy as np



def random_sampler(filename, k):
    sample = []
    with open(filename, 'rb') as f:
        linecount = sum(1 for line in f)
        f.seek(0)

        random_linenos = sorted(random.sample(range(linecount), k), reverse = True)
        lineno = random_linenos.pop()
        for n, line in enumerate(f):
            if n == lineno:
                sample.append(line.rstrip())
                if len(random_linenos) > 0:
                    lineno = random_linenos.pop()
                else:
                    break
    f.close()
    return sample



n, p = 1, .3
words_per_line = int(sys.argv[3])
lines_in_text = int(sys.argv[2])
with open ("benchmark.txt", "a") as out:
    out.truncate(0)
out.close()
for reps in range(lines_in_text):

    s = np.random.binomial(n, p, words_per_line)
    # print(s)
    k = words_per_line-sum(s)
    a = random_sampler(sys.argv[1], k)
    # print (a)
    wds = list(map(lambda x: x.decode("utf-8"), a))
    # print (wds)
    with open ("benchmark.txt", "a") as out:
        for element in s:
            if element:
                out.write('pepega ')
            else:
                out.write(wds.pop()+' ')
        out.write('\n')
    out.close()
