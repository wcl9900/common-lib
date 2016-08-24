package com.arta.lib.widget.customfastscrollview;

/**
 * Simple Function, designed after Google Guava's Function object.
 * @author nolan
 *
 * @param <E>
 * @param <T>
 */
public interface Function<E,T> {
	
	public T apply(E input);

}
