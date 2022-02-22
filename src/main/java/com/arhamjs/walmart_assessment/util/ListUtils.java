package com.arhamjs.walmart_assessment.util;

import com.arhamjs.walmart_assessment.ticket.SeatingAssignment;

import java.util.ArrayList;
import java.util.List;

public final class ListUtils {
    private ListUtils() {
    }

    public static void retainAllOrdered(List<SeatingAssignment> source, List<SeatingAssignment> other) {
        source.retainAll(other);

        List<SeatingAssignment> otherCopy = new ArrayList<>(other);
        otherCopy.retainAll(source);

        List<SeatingAssignment> copy = new ArrayList<>(source);
        for (int index = 0; index < copy.size(); index++) {
            SeatingAssignment sourceAssignment = copy.get(index);
            int otherIndex = otherCopy.indexOf(sourceAssignment);

            int averageIndex = (index + otherIndex) / 2;
            if (index != averageIndex) {
                source.remove(sourceAssignment);
                source.add(averageIndex, sourceAssignment);
            }
        }
    }
}
