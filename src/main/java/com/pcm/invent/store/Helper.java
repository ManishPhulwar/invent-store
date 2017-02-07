package com.pcm.invent.store;

import org.bson.types.ObjectId;

public class Helper {

	public static String newId() {
		return ObjectId.get().toHexString();
	}

}
