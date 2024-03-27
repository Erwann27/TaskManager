package ToDoList;

public interface ToDoList {

    /**
     * @param task
     * adds a task to the TodoList
     */
    void addTask(Task task);

    /**
     * @param task
     * deletes a task from the ToDoList
     */
    void deleteTask(Task task);
}
