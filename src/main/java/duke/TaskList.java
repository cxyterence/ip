package duke;

import duke.exception.EmptyDescException;
import duke.task.*;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class TaskList {

    private ArrayList<Task> taskArrayList;

    public TaskList(Scanner s) {
        taskArrayList = new ArrayList<>();
        while (s.hasNext()) {
            commandBuilder(s.nextLine());
        }
    }

    /**
     * Builds the command that determines which tasks to be added, according to the save file.
     * @param saveLine the line of data from the save file.
     */
    private void commandBuilder(String saveLine) {
        int divideIndex;
        String commandType = saveLine.substring(0, 1);
        int isDone = Integer.parseInt(saveLine.substring(4, 5));
        String commandDesc = saveLine.substring(8);

        switch(commandType) {
            case "T":
                try {
                    ToDo task = new ToDo(" " + commandDesc);
                    if (isDone == 1) { task.markDone(); }
                    taskArrayList.add(task);
                } catch (EmptyDescException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "D":
                try {
                    divideIndex = commandDesc.lastIndexOf("|");
                    String commandBy = commandDesc.substring(divideIndex + 2);
                    commandDesc = " " + commandDesc.substring(0 , divideIndex - 1);
                    Deadline task = new Deadline(commandDesc, commandBy);
                    if (isDone == 1) { task.markDone(); }
                    taskArrayList.add(task);
                } catch (EmptyDescException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "E":
                try {
                    divideIndex = commandDesc.lastIndexOf("|");
                    String commandBy = commandDesc.substring(divideIndex + 2);
                    commandDesc = " " + commandDesc.substring(0 , divideIndex - 1);
                    Event task = new Event(commandDesc, commandBy);
                    if (isDone == 1) { task.markDone(); }
                    taskArrayList.add(task);
                } catch (EmptyDescException e) {
                    System.out.println(e.getMessage());
                }
                break;
        }
    }

    /**
     * Method to add a task.
     */
    public void addTask(Task task) {
        taskArrayList.add(task);
    }

    public void deleteTask(int index) {
        taskArrayList.remove(index - 1);
    }

    public void markTask(int index) { taskArrayList.get(index-1).markDone(); }

    public void unmarkTask(int index) { taskArrayList.get(index-1).unmarkDone(); }

    public Task getTask(int index) {
        return taskArrayList.get(index-1);
    }

    public int listSize() {
        return taskArrayList.size();
    }

    public ListIterator<Task> getIterator() {
        return taskArrayList.listIterator();
    }

    /**
     * Method to execute "list" command.
     * Prints out the tasks stored in the array list.
     */
    public void printTaskArray() {
        ListIterator<Task> iterate = taskArrayList.listIterator();
        System.out.println("Here are the tasks in your list:");
        int qty = 0;
        while (iterate.hasNext()) {
            qty++;
            System.out.println(qty + "." + iterate.next().toString());
        }
    }
}