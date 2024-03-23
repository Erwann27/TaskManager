package TaskFactory;

import Task.BooleanTask;
import Task.ComplexTask;
import Task.Priority;
import Task.ProgressiveTask;

import java.util.Date;

public interface TaskFactory {
    BooleanTask createBooleanTask(String description, Date deadline, Priority priority, int estimatedTime);
    ProgressiveTask createProgressiveTask(String description, Date deadline, Priority priority, int estimatedTime);
    ComplexTask createComplexTask(String description, Priority priority);
}
