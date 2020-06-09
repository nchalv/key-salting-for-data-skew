## Spark task Data Visualization

This project is focusing on collecting data of jobs executed on Spark from the
Spark Job History server and visualizing the duration of aggregation operation
tasks per stage (mini-batch) in the *workdcount* example we are using.

The intention is to use this information as a proof of concept in our claim that
we can improve the performance of aggregations on skewed datasets.

Execute with: `python3 visualize_red_task_duration.py $job_id`.
