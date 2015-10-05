package com.example.mohamedibrahim.multilevelmenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohamed Ibrahim on 10/5/2015.
 */
public class Section {

    private int id;
    private String name;
    private List<Section> subSections;


    public Section(int id, String name, List<Section> subSections) {

        this.id = id;
        this.name = name;
        this.subSections = subSections;

    }

    public int getId() {
        return id;
    }

    public List<Section> getSubSections() {
        return subSections;
    }

    public String getName() {
        return name;
    }


    public static List<Section> generateSections() {

        final List<Section> sections = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {

            final Section section = new Section(i, "section " + i, getSubSection(i));
            sections.add(section);
        }
        return sections;
    }

    private static List<Section> getSubSection(int c) {
        final List<Section> sections = new ArrayList<>();
        for (int i = 0; i <  2; i++) {
            final Section section = new Section(i, "section " + i, null);
            sections.add(section);
        }
        return sections;
    }


}
