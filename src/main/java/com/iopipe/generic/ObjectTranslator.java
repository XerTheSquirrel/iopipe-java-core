package com.iopipe.generic;

import java.lang.reflect.Type;

/**
 * This class manages converters to the target type.
 *
 * @since 2018/08/20
 */
public abstract class ObjectTranslator
{
	/** The type to convert from. */
	protected final Type from;
	
	/** The type to convert to. */
	protected final Type to;
	
	/**
	 * Initializes a translator to the given type.
	 *
	 * @param __f The from type.
	 * @param __t The target type
	 * @throws NullPointerException On null arguments.
	 * @since 2018/08/20
	 */
	ObjectTranslator(Type __f, Type __t)
		throws NullPointerException
	{
		if (__f == null || __t == null)
			throw new NullPointerException();
		
		this.from = __f;
		this.to = __t;
	}
	
	/**
	 * Translates from the given object to the destination object.
	 *
	 * @param __f The object to translate, if this is null then no object
	 * is translated and null is returned.
	 * @return The result of the translation, if {@code __f} is null then this
	 * will return null.
	 * @since 2018/08/20
	 */
	public abstract Object translate(Object __f);
	
	/**
	 * {@inheritDoc}
	 * @since 2018/08/20
	 */
	@Override
	public final boolean equals(Object __o)
	{
		if (this == __o)
			return true;
		
		if (!(__o instanceof ObjectTranslator))
			return false;
		
		ObjectTranslator o = (ObjectTranslator)__o;
		return this.from.equals(o.from) &&
			this.to.equals(o.to);
	}
	
	/**
	 * Returns the from type.
	 *
	 * @return The from type.
	 * @since 2018/08/22
	 */
	public final Type from()
	{
		return this.from;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/08/20
	 */
	@Override
	public final int hashCode()
	{
		return this.from.hashCode() ^ this.to.hashCode();
	}
	
	/**
	 * Returns the to type.
	 *
	 * @return The to type.
	 * @since 2018/08/22
	 */
	public final Type to()
	{
		return this.to;
	}
	
	/**
	 * Creates a translator for converting objects to the given type.
	 *
	 * @param __f The from type.
	 * @param __t The to class.
	 * @return A translator to translate from one class to the other.
	 * @since 2018/08/20
	 */
	public static final ObjectTranslator translator(Type __f, Type __t)
		throws NullPointerException
	{
		if (__f == null || __t == null)
			throw new NullPointerException();
		
		return new __JacksonConvert__(__f, __t);
	}
}

