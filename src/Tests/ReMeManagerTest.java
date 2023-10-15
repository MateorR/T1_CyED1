package Tests;

import model.ReMeManager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class ReMeManagerTest {
    @Test
    public void addTest() {
        ReMeManager controller = new ReMeManager();
        assertEquals("TASK added successful", controller.addAssignment("title", "description", "2021-05-05 12:00:00", 1, 0));
        assertEquals("There is already an assignment with that title", controller.addAssignment("title", "description", "2021-05-05 12:00:00", 1, 1));
    }

    @Test
    public void removeTest() {
        ReMeManager controller = new ReMeManager();
        controller.addAssignment("title", "description", "2021-05-05 12:00:00", 1, 0);
        assertEquals("Assignment removed successful", controller.removeAssignment("title"));
        assertEquals("There are no assignment with that title", controller.removeAssignment("title"));
    }

    @Test
    public void modifyTest() {
        ReMeManager controller = new ReMeManager();
        controller.addAssignment("title", "description", "2021-05-05 12:00:00", 1, 0);
        assertEquals("Assignment modified successful", controller.modifyAssignment("title", 1, "description2"));
        assertEquals("description2", controller.findAssignment("title").getDescription());
    }

    @Test
    public void showTaskTest() {
        ReMeManager controller = new ReMeManager();
        controller.addAssignment("title", "description", "2021-05-05 12:00:00", 1, 0);
        assertEquals("TASK added successful", controller.addAssignment("title2", "description2", "2021-05-05 12:00:00", 1, 0));
        assertEquals("TASK added successful", controller.addAssignment("title3", "description3", "2021-05-05 12:00:00", 1, 0));
        assertEquals("TASK added successful", controller.addAssignment("title4", "description4", "2021-05-05 12:00:00", 1, 0));
        assertEquals("""
                title2 description2 2021-05-05 12:00:00.0 1 false TASK
                title description 2021-05-05 12:00:00.0 1 false TASK
                title4 description4 2021-05-05 12:00:00.0 1 false TASK
                title3 description3 2021-05-05 12:00:00.0 1 false TASK
                """, controller.showTasks());
    }

    @Test
    public void showReminderTest() {
        ReMeManager controller = new ReMeManager();
        controller.addAssignment("title", "description", "2021-05-05 12:00:00", 1, 1);
        assertEquals("REMINDER added successful", controller.addAssignment("title2", "description2", "2021-05-05 12:00:00", 1, 1));
        assertEquals("REMINDER added successful", controller.addAssignment("title3", "description3", "2021-05-05 12:00:00", 1, 1));
        assertEquals("REMINDER added successful", controller.addAssignment("title4", "description4", "2021-05-05 12:00:00", 1, 1));
        assertEquals("""
                title2 description2 2021-05-05 12:00:00.0 1 false REMINDER
                title description 2021-05-05 12:00:00.0 1 false REMINDER
                title4 description4 2021-05-05 12:00:00.0 1 false REMINDER
                title3 description3 2021-05-05 12:00:00.0 1 false REMINDER
                """, controller.showReminders());
    }

    @Test
    public void showAllTest() {
        ReMeManager controller = new ReMeManager();
        controller.addAssignment("title", "description", "2021-05-05 12:00:00", 1, 0);
        assertEquals("REMINDER added successful", controller.addAssignment("title2", "description2", "2021-05-05 12:00:00", 0, 1));
        assertEquals("REMINDER added successful", controller.addAssignment("title3", "description3", "2021-05-05 12:00:00", 1, 1));
        assertEquals("REMINDER added successful", controller.addAssignment("title4", "description4", "2021-05-05 12:00:00", 0, 1));
        assertEquals("""
                title2 description2 2021-05-05 12:00:00.0 0 false REMINDER
                title description 2021-05-05 12:00:00.0 1 false TASK
                title4 description4 2021-05-05 12:00:00.0 0 false REMINDER
                title3 description3 2021-05-05 12:00:00.0 1 false REMINDER
                """, controller.showAll());
    }

    @Test
    public void showPriorityAssignmentsTest() {
        ReMeManager controller = new ReMeManager();
        controller.addAssignment("title", "description", "2021-05-05 12:00:00", 1, 0);
        assertEquals("REMINDER added successful", controller.addAssignment("title2", "description2", "2021-05-05 12:00:00", 1, 1));
        assertEquals("REMINDER added successful", controller.addAssignment("title3", "description3", "2021-05-05 12:00:00", 0, 1));
        assertEquals("REMINDER added successful", controller.addAssignment("title4", "description4", "2021-05-05 12:00:00", 1, 1));
        assertEquals("""
                title2 description2 2021-05-05 12:00:00.0 1 false REMINDER
                title4 description4 2021-05-05 12:00:00.0 1 false REMINDER
                title description 2021-05-05 12:00:00.0 1 false TASK
                """, controller.showPriorityAssignments());
    }

    @Test
    public void showPriorityAssignmentsByDateTest() {
        ReMeManager controller = new ReMeManager();
        controller.addAssignment("title", "description", "2018-05-05 12:00:00", 1, 0);
        assertEquals("REMINDER added successful", controller.addAssignment("title2", "description2", "2021-05-05 12:00:00", 1, 1));
        assertEquals("REMINDER added successful", controller.addAssignment("title3", "description3", "2020-05-05 12:00:00", 0, 1));
        assertEquals("REMINDER added successful", controller.addAssignment("title4", "description4", "2022-05-05 12:00:00", 1, 1));
        assertEquals("""
                title description 2018-05-05 12:00:00.0 1 false TASK
                title2 description2 2021-05-05 12:00:00.0 1 false REMINDER
                title4 description4 2022-05-05 12:00:00.0 1 false REMINDER
                """, controller.showPriorityAssignmentsByDate());
    }

    @Test
    public void showNonPriorityAssignmentsTest() {
        ReMeManager controller = new ReMeManager();
        controller.addAssignment("title", "description", "2021-05-05 12:00:00", 1, 0);
        assertEquals("REMINDER added successful", controller.addAssignment("title2", "description2", "2021-05-05 12:00:00", 1, 1));
        assertEquals("REMINDER added successful", controller.addAssignment("title3", "description3", "2021-05-05 12:00:00", 0, 1));
        assertEquals("""
                title3 description3 2021-05-05 12:00:00.0 0 false REMINDER
                """, controller.showNonPriorityAssignments());
    }

    @Test
    public void undoTest() {
        ReMeManager controller = new ReMeManager();
        controller.addAssignment("title", "description", "2021-05-05 12:00:00", 1, 0);
        controller.addAssignment("title2", "description2", "2021-05-05 12:00:00", 1, 0);
        controller.addAssignment("title3", "description3", "2021-05-05 12:00:00", 1, 0);
        controller.addAssignment("title4", "description4", "2021-05-05 12:00:00", 1, 0);
        controller.undo();
        assertEquals("""
                title2 description2 2021-05-05 12:00:00.0 1 false TASK
                title description 2021-05-05 12:00:00.0 1 false TASK
                title3 description3 2021-05-05 12:00:00.0 1 false TASK
                """, controller.showTasks());
    }

}
