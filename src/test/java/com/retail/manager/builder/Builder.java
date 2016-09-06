package com.retail.manager.builder;
/**
 * This is an interface to create Builder Objects
 * @author Pulkit Garg
 *
 */
public interface Builder<DomainObject> {
	DomainObject build();
}
