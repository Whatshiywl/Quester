package com.gmail.molnardad.quester;

import java.util.HashSet;
import java.util.Set;

public enum QuestFlag {

	ACTIVE (0),
	ORDERED (1),
	UNCANCELLABLE (2),
	ONLYFIRST (3);

    private final int type;
    QuestFlag (int type) {
        this.type = type;
    }
    
    public int getType() {
    	return type;
    }
    
    public static QuestFlag getByName(String name) {
    	try {
    		return valueOf(name.toUpperCase());
    	} catch (Exception e) {}
    	
    	return null;
    }
    
    public static String stringize(Set<QuestFlag> flags) {
    	String result = "";
		String gl = ", ";
		boolean first = true;
		for(QuestFlag f : flags) {
			if(first) {
				result += f.name();
				first = false;
			} else 
				result += gl + f.name();
		}
		return result;
    }
    
    public static String serialize(Set<QuestFlag> flags) {
    	String result = "";
    	for(QuestFlag f : flags)
    		result += f.name() + ";";
    	return result;
    }
    
    public static Set<QuestFlag> deserialize(String input) {
    	Set<QuestFlag> flags = new HashSet<QuestFlag>();
    	
    	for(String s : input.split(";")) {
    		QuestFlag f = QuestFlag.getByName(s);
    		if(f != null)
    			flags.add(f);
    	}
    	return flags;
    }
}