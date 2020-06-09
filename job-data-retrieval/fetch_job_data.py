import requests
import sys
import numpy as np
import matplotlib.pyplot as plt


JH_SERVER = 'http://10.0.1.125:18080/'
APP_ID = sys.argv[1]
#SHUFFLE_PARTITIONS = 12

r = requests.get(JH_SERVER+'api/v1/applications/'+APP_ID+'/1/stages')
j = r.json()
l = len(j)
dt = {}
r = requests.get(JH_SERVER+'api/v1/applications/'+APP_ID+'/1/stages/1/0/taskList')
SHUFFLE_PARTITIONS=len(r.json())
for k in range (SHUFFLE_PARTITIONS):
    dt[str(k)] = []
#print(dt)
lb =[]

# print(SHUFFLE_PARTITIONS)
for i in range(1,l+1):
    #print(i)
    r = requests.get(JH_SERVER+'api/v1/applications/'+APP_ID+'/1/stages/'+str(i)+'/0/taskList')
    j = r.json()
    if len(j) == SHUFFLE_PARTITIONS:
        lb.append(str(i))
        for element in j:
            dt[str(element['index'])].append(element['duration'])
            #print (str(element['index'])+': '+str(element['duration']))
        #print(d)

#dt=np.array(data)
#print(dt)


# set width of bar
barWidth = 1/(SHUFFLE_PARTITIONS+1)

# Set position of bar on X axis
r={}
r['0']=np.arange(len(dt['0']))
for m in range (1, SHUFFLE_PARTITIONS):
    r[str(m)] = np.array([x + barWidth for x in r[str(m-1)]])

# print(r['0'])
# print(r['1'])
# for m in range ( SHUFFLE_PARTITIONS):
#     print (r[str(m)])
for m in range (SHUFFLE_PARTITIONS):
    plt.bar(r[str(m)], dt[str(m)], width=barWidth, edgecolor='white', label=str(m))



# Add xticks on the middle of the group bars
plt.xlabel('stage', fontweight='bold')
plt.xticks([r + barWidth*(SHUFFLE_PARTITIONS-1)/2 for r in range(len(dt['0']))], lb)


plt.legend()
plt.show()

#plt.hist(dt)  # `density=False` would make counts
#plt.show()
