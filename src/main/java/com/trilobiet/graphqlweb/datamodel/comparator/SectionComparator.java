package com.trilobiet.graphqlweb.datamodel.comparator;

import java.util.Comparator;

import com.trilobiet.graphqlweb.datamodel.Section;

public class SectionComparator implements Comparator<Section> {

	@Override
	public int compare(Section s1, Section s2) {

		Comparator<Section> c = Comparator.comparing(section -> section.getGroupNumber());
		c = c.thenComparing( section -> section.getIndex() );
		c = c.thenComparing( section -> section.getName()!=null?section.getName():"" );
		// add more comparisons if needed

		return c.compare(s1, s2);
	}

}
