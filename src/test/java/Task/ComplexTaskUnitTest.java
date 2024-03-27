package Task;

import ToDoList.ComplexTask;
import ToDoList.Priority;
import ToDoList.SimpleTask;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.*;

class ComplexTaskUnitTest {

    @Test
    public void shouldCreateComplexTask() {
        // GIVEN
        String desc = "TEST";
        Priority priority = Priority.MINOR;
        ComplexTask task;
        // WHEN
        task = new ComplexTask(desc, priority);
        // THEN
        assertThat(task).isNotNull();
        assertThat(task.getDescription()).isEqualTo(desc);
        assertThat(task.getPriority()).isEqualTo(priority);
    }

    @Test
    public void shouldAddSubtask() {
        // GIVEN
        int time1 = 1;
        int time2 = 4;
        String desc = "TEST";
        Priority priority = Priority.MINOR;
        ComplexTask task = new ComplexTask(desc, priority);
        SimpleTask task1 = Mockito.mock(SimpleTask.class);
        // WHEN
        task.addSubTask(task1);
        // THEN
        assertThat(task.getSubTasks()).contains(task1);
    }

    @Test
    public void shouldComputeEstimatedTime() {
        // GIVEN
        int time1 = 1;
        int time2 = 4;
        String desc = "TEST";
        Priority priority = Priority.MINOR;
        ComplexTask task = new ComplexTask(desc, priority);
        SimpleTask task1 = Mockito.mock(SimpleTask.class);
        Mockito.when(task1.getEstimatedTimeInDays()).thenReturn(time1);
        task.addSubTask(task1);
        SimpleTask task2 = Mockito.mock(SimpleTask.class);
        Mockito.when(task2.getEstimatedTimeInDays()).thenReturn(time2);
        task.addSubTask(task2);
        task.addSubTask(task);
        int result;
        // WHEN
        result = task.getEstimatedTimeInDays();
        // THEN
        assertThat(result).isEqualTo(time1 + time2);
    }

    @Test
    public void shouldReturnNullBecauseNoSubtasks() {
        // GIVEN
        String desc = "TEST";
        Priority priority = Priority.MINOR;
        ComplexTask task = new ComplexTask(desc, priority);
        Date result;
        // WHEN
        result = task.getDeadline();
        // THEN
        assertThat(result).isNull();
    }

    @Test
    public void shouldComputeMaxDeadline() {
        // GIVEN
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1);
        Date date1 = calendar.getTime();
        Date date2 = new Date();
        String desc = "TEST";
        Priority priority = Priority.MINOR;
        ComplexTask task = new ComplexTask(desc, priority);
        SimpleTask task1 = Mockito.mock(SimpleTask.class);
        Mockito.when(task1.getDeadline()).thenReturn(date1);
        task.addSubTask(task1);
        SimpleTask task2 = Mockito.mock(SimpleTask.class);
        Mockito.when(task2.getDeadline()).thenReturn(date2);
        task.addSubTask(task2);
        task.addSubTask(task);
        Date result;
        // WHEN
        result = task.getDeadline();
        // THEN
        assertThat(result).isEqualTo(date2);
    }

    @Test
    public void shouldComputeProgress() {
        // GIVEN
        int time1 = 1;
        int time2 = 4;
        double progress1 = 0;
        double progress2 = 100;
        String desc = "TEST";
        Priority priority = Priority.MINOR;
        ComplexTask task = new ComplexTask(desc, priority);
        SimpleTask task1 = Mockito.mock(SimpleTask.class);
        Mockito.when(task1.getEstimatedTimeInDays()).thenReturn(time1);
        Mockito.when(task1.getProgress()).thenReturn(progress1);
        task.addSubTask(task1);
        SimpleTask task2 = Mockito.mock(SimpleTask.class);
        Mockito.when(task2.getEstimatedTimeInDays()).thenReturn(time2);
        Mockito.when(task2.getProgress()).thenReturn(progress2);
        task.addSubTask(task2);
        task.addSubTask(task);
        double result;
        // WHEN
        result = task.getProgress();
        // THEN
        assertThat(result).isEqualTo(((progress1 * time1) +
                (progress2 * time2)) / (time1 + time2));
    }

    @Test
    public void shouldComputeProgressWhenTimeIsZero() {
        // GIVEN
        int time1 = 0;
        double progress1 = 0;
        String desc = "TEST";
        Priority priority = Priority.MINOR;
        ComplexTask task = new ComplexTask(desc, priority);
        SimpleTask task1 = Mockito.mock(SimpleTask.class);
        Mockito.when(task1.getEstimatedTimeInDays()).thenReturn(time1);
        Mockito.when(task1.getProgress()).thenReturn(progress1);
        task.addSubTask(task1);
        double result;
        // WHEN
        result = task.getProgress();
        // THEN
        assertThat(result).isEqualTo(100);
    }

}