/**
 * 
 */
package org.openmrs.module.dss.test.util;

import java.util.Calendar;

import org.openmrs.module.chirdlutil.util.Util;

import junit.framework.TestCase;

/**
 * @author tmdugan
 * 
 */
public class TestUtil extends TestCase
{
	/**
	 * 
	 */
	public void testConvertUnitsToMetric()
	{
		assertEquals(20.0, Util.convertUnitsToMetric(20, null));

		assertEquals(50.8, Util.convertUnitsToMetric(20, "in"));

		assertEquals(9.071847400000001, Util.convertUnitsToMetric(20, "lb"));

		assertEquals(20.0, Util.convertUnitsToMetric(20, "cm"));

	}
	
	public void testToProperCase()
	{
		String str = "firstname lastname";
		
		assertEquals("Firstname Lastname",Util.toProperCase(str));
		
		str = "lastname";
		
		assertEquals("Lastname",Util.toProperCase(str));
		
		str = "";
		
		assertEquals("",Util.toProperCase(str));
		
		str = null;
		
		assertEquals(null,Util.toProperCase(str));
	}
	
	public void testFractionalUnits()
	{
		Calendar today = Calendar.getInstance();
		today.set(2008, Calendar.JUNE,1);
		Calendar birthdate = Calendar.getInstance();
		birthdate.set(2007, Calendar.SEPTEMBER, 9);
		double fractAge = Util.getFractionalAgeInUnits(birthdate.getTime(), 
				today.getTime(), "mo");
		assertEquals(fractAge,8.741935483870968);
		
		today = Calendar.getInstance();
		today.set(2008, Calendar.JUNE,27);
		birthdate = Calendar.getInstance();
		birthdate.set(2007, Calendar.SEPTEMBER, 9);
		fractAge = Util.getFractionalAgeInUnits(birthdate.getTime(), 
				today.getTime(), "mo");
		assertEquals(fractAge,9.6);
	}
	
	public void testRound()
	{
		assertEquals(Util.round(25.486546543, 1),25.5);
		assertEquals(Util.round(.486546543, 2),.49);
		assertEquals(Util.round(.111, 1),.1);
		assertEquals(Util.round(.002, 1),0D);
		assertEquals(Util.round(.002, 4),0.0020);
	}
}
