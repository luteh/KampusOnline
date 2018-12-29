package com.luteh.kampusonline.ui.fragments.rencanastudi.adapter;

import com.luteh.kampusonline.model.rencanastudi.FRencanaStudi;
import com.luteh.kampusonline.model.rencanastudi.Semester;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Luthfan Maftuh on 29/12/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class RencanaStudiGroupDataFactory {

    public static List<RencanaStudiGroup> makeRencanaStudiGroups(FRencanaStudi fRencanaStudi) {
        return Arrays.asList(
                makeGroupItems("Semester 1", fRencanaStudi.semester_1),
                makeGroupItems("Semester 2", fRencanaStudi.semester_2),
                makeGroupItems("Semester 3", fRencanaStudi.semester_3),
                makeGroupItems("Semester 4", fRencanaStudi.semester_4),
                makeGroupItems("Semester 5", fRencanaStudi.semester_5),
                makeGroupItems("Semester 6", fRencanaStudi.semester_6),
                makeGroupItems("Semester 7", fRencanaStudi.semester_7),
                makeGroupItems("Semester 8", fRencanaStudi.semester_8)
        );
    }

    private static RencanaStudiGroup makeGroupItems(String title, List<Semester> semesterList) {
        return new RencanaStudiGroup(title, semesterList);
    }
}
