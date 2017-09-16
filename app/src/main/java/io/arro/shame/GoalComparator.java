package io.arro.shame;

import java.util.Comparator;

/**
 * Created by saybe on 2017-09-16.
 */

public class GoalComparator implements Comparator<Goal> {
    @Override
    public int compare(Goal goal1, Goal goal2) {
        return goal1.endDate.compareTo(goal2.endDate);
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("WHY WAS THIS RUN?");
        return false;
    }
}
