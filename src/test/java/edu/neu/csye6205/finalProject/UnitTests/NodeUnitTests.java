package edu.neu.csye6205.finalProject.UnitTests;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Test;

import edu.neu.csye6205.finalProject.Paurush.Node;

public class NodeUnitTests {

	@Test
	public void testConstructorAndGetters() {
		Node node = new Node("A", 37.7749, -122.4194);
		assertEquals("A", node.getId());
		assertEquals(37.7749 * Math.PI / 180, node.getLatitude(), 0.0001);
		assertEquals(-122.4194 * Math.PI / 180, node.getLongitude(), 0.0001);
	}

	@Test
	public void testSetters() {
		Node node = new Node("A", 37.7749, -122.4194);
		node.setId("B");
		node.setLatitude(38.8895);
		node.setLongitude(-77.0352);
		assertEquals("B", node.getId());
		assertEquals(38.8895 * Math.PI / 180, node.getLatitude() * Math.PI / 180, 0.0001);
		assertEquals(-77.0352 * Math.PI / 180, node.getLongitude() * Math.PI / 180, 0.0001);
	}

	@Test
	public void testToString() {
		Node node = new Node("A", 37.7749, -122.4194);
		assertEquals("A", node.toString());
	}

	@Test
	public void testGetName() {
		Node node = new Node("A", 37.7749, -122.4194);
		assertEquals("A", node.getName());
	}

	@Test
	public void testGetPosition() {
		Node node = new Node("A", 37.7749, -122.4194);
		Point point = node.getPosition();
		assertEquals((int) (37.7749 * Math.PI / 180), point.x);
		assertEquals((int) (-122.4194 * Math.PI / 180), point.y);
	}
}
