# task-tracker-01
Java project to keep track of tasks, pending, progress of tasklists, and priority

# RestAPI Overview

## • TaskList Endpoints
```
GET      /task-lists                   List Task Lists  
POST     /task-lists                   Create Task Lists  
GET      /task-lists/{task_list_id}    Get Task List by ID  
PUT      /task-lists/{task_list_id}    Update Task list  
DELETE   /task-lists/{task_list_id}    Delete Task List  
```
## • Task Endpoints
```
GET     /task-lists/{task_list_id}/tasks              List TASKS
POST    /task-lists/{task_list_id}/tasks              Create TASK
GET     /task-lists/{task_list_id}/tasks/{task_id}    Get TASK by ID
PUT     /task-lists/{task_list_id}/tasks/{task_id}    Update TASK
DELETE  /task-lists/{task_list_id}/tasks/{task_id}    Delete TASK
```
