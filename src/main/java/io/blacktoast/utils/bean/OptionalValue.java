package io.blacktoast.utils.bean;

import java.util.Optional;

public class OptionalValue{
	
	public static <T> Optional<T> val(T obj) {
		return Optional.ofNullable(obj);
	}

}
