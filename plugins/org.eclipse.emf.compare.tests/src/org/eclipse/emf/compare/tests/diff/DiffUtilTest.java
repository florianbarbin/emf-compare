/*******************************************************************************
 * Copyright (c) 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.compare.tests.diff;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertSame;

import com.google.common.collect.Lists;

import java.util.List;

import org.eclipse.emf.compare.CompareFactory;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.EMFCompareConfiguration;
import org.eclipse.emf.compare.utils.DiffUtil;
import org.junit.Test;

/**
 * We will use this to test the utility methods exposed by the {@link DiffUtil}.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
@SuppressWarnings("all")
public class DiffUtilTest {
	@Test
	public void lcsTest1() {
		final List<Character> left = Lists.charactersOf("abcde");
		final List<Character> right = Lists.charactersOf("czdab");

		final Comparison emptyComparison = createEmptyComparison();
		final List<Character> lcs = DiffUtil.longestCommonSubsequence(emptyComparison, left, right);

		/*
		 * This is documented in {@link DefaultDiffEngine#longestCommonSubsequence(Comparison, List, List)}.
		 * Ensure the documentation stays in sync.
		 */
		assertEqualContents(Lists.charactersOf("cd"), lcs);
	}

	@Test
	public void lcsTest2() {
		final List<Character> left = Lists.charactersOf("abcde");
		final List<Character> right = Lists.charactersOf("ycdeb");

		final Comparison emptyComparison = createEmptyComparison();
		final List<Character> lcs = DiffUtil.longestCommonSubsequence(emptyComparison, left, right);

		/*
		 * This is documented in {@link DiffUtil#longestCommonSubsequence(Comparison, List, List)}. Ensure the
		 * documentation stays in sync.
		 */
		assertEqualContents(Lists.charactersOf("cde"), lcs);
	}

	@Test
	public void lcsTest3() {
		final List<Integer> left = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7);
		final List<Integer> right = Lists.newArrayList(8, 9, 2, 3, 4, 1, 0);

		final Comparison emptyComparison = createEmptyComparison();
		final List<Integer> lcs = DiffUtil.longestCommonSubsequence(emptyComparison, left, right);

		// These are the origin and left sides of the "complex" conflict test case.
		assertEqualContents(Lists.newArrayList(2, 3, 4), lcs);
	}

	@Test
	public void lcsTest4() {
		final List<Integer> left = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7);
		final List<Integer> right = Lists.newArrayList(6, 2, 9, 3, 0, 4, 1, 7);

		final Comparison emptyComparison = createEmptyComparison();
		final List<Integer> lcs = DiffUtil.longestCommonSubsequence(emptyComparison, left, right);

		// These are the origin and right sides of the "complex" conflict test case.
		assertEqualContents(Lists.newArrayList(2, 3, 4, 7), lcs);
	}

	@Test
	public void insertionIndexTest1() {
		// Assume "left" is {8, 9, 2, 3, 4, 1, 0, 6}
		// Assume "right" is {6, 2, 9, 3, 0, 4, 7}
		// We'll transition "right" into "left" by "merging" the additions one after another.
		// We'll assume the user merges all from left to right, fixing conflicts by "undoing" changes in right

		// We'll go through the following changes :
		// add "1" in right = {6, 2, 9, 3, 1, 0, 4, 7}
		// remove 9 from right = {6, 2, 3, 1, 0, 4, 7}
		// add "9" in right = {6, 9, 2, 3, 1, 0, 4, 7}
		// remove "0" from right = {6, 9, 2, 3, 1, 4, 7}
		// add "0" in right = {6, 9, 2, 3, 1, 0, 4, 7}
		// add "8" in right = {6, 8, 9, 2, 3, 1, 0, 4, 7}
		// remove "7" from right = {6, 8, 9, 2, 3, 1, 0, 4}
		// remove "4" from right = {6, 8, 9, 2, 3, 1, 0}
		// add "4" in right = {6, 8, 9, 2, 3, 4, 1, 0}
		// remove "6" from right = {8, 9, 2, 3, 4, 1, 0}
		// add "6" in right = {8, 9, 2, 3, 4, 1, 0, 6}

		final List<Integer> left = Lists.newArrayList(8, 9, 2, 3, 4, 1, 0, 6);
		final Comparison emptyComparison = createEmptyComparison();

		// Merge the move of "1" (assume 1 already removed from right)
		List<Integer> right = Lists.newArrayList(6, 2, 9, 3, 0, 4, 7);
		int insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(1));
		// Inserted just before "0"
		assertSame(Integer.valueOf(4), Integer.valueOf(insertionIndex));

		// Merge the move of "9" (assume 9 already removed from right)
		right = Lists.newArrayList(6, 2, 3, 1, 0, 4, 7);
		insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(9));
		// Inserted just before "2"
		assertSame(Integer.valueOf(1), Integer.valueOf(insertionIndex));

		// Merge the move of "0" (assume 0 already removed from right)
		right = Lists.newArrayList(6, 9, 2, 3, 1, 4, 7);
		insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		// Inserted just before "4"
		assertSame(Integer.valueOf(5), Integer.valueOf(insertionIndex));

		// merge the addition of "8"
		right = Lists.newArrayList(6, 9, 2, 3, 1, 0, 4, 7);
		insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(8));
		// Inserted just before "9"
		assertSame(Integer.valueOf(1), Integer.valueOf(insertionIndex));

		// remove "7"... right = {6, 8, 9, 2, 3, 1, 0, 4}

		// merge the move of "4" (assume already removed from right)
		right = Lists.newArrayList(6, 8, 9, 2, 3, 1, 0);
		insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(4));
		// Inserted just after "3"
		assertSame(Integer.valueOf(5), Integer.valueOf(insertionIndex));

		// merge the move of "6" (assume already removed from right)
		right = Lists.newArrayList(8, 9, 2, 3, 4, 1, 0);
		insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(6));
		// Inserted just after "0"
		assertSame(Integer.valueOf(7), Integer.valueOf(insertionIndex));
	}

	@Test
	public void insertionIndexTest2() {
		// Try and insert between two lists with no common element
		final List<Integer> right = Lists.newArrayList(4, 5, 6);
		final Comparison emptyComparison = createEmptyComparison();
		// We'll add "0" in right and expect it to be added at the end wherever its location in left
		final Integer expectedIndex = Integer.valueOf(right.size());

		List<Integer> left = Lists.newArrayList(0, 1, 2, 3);
		int insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		assertSame(expectedIndex, Integer.valueOf(insertionIndex));

		left = Lists.newArrayList(1, 0, 2, 3);
		insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		assertSame(expectedIndex, Integer.valueOf(insertionIndex));

		left = Lists.newArrayList(1, 2, 3, 0);
		insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		assertSame(expectedIndex, Integer.valueOf(insertionIndex));
	}

	@Test
	public void insertionIndexTest3() {
		// Try and insert an element before the LCS, LCS being the whole second list
		final List<Integer> right = Lists.newArrayList(1, 2, 3);
		final Comparison emptyComparison = createEmptyComparison();
		// We'll add "0" in right and expect it to be added at the beginning
		final Integer expectedIndex = Integer.valueOf(0);

		List<Integer> left = Lists.newArrayList(0, 1, 2, 3);
		int insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		assertSame(expectedIndex, Integer.valueOf(insertionIndex));

		left = Lists.newArrayList(0, 4, 1, 2, 3);
		insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		assertSame(expectedIndex, Integer.valueOf(insertionIndex));

		left = Lists.newArrayList(4, 0, 1, 2, 3);
		insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		assertSame(expectedIndex, Integer.valueOf(insertionIndex));

		left = Lists.newArrayList(4, 0, 5, 1, 2, 3);
		insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		assertSame(expectedIndex, Integer.valueOf(insertionIndex));

		left = Lists.newArrayList(4, 0, 5, 1, 2, 3, 6);
		insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		assertSame(expectedIndex, Integer.valueOf(insertionIndex));

		left = Lists.newArrayList(4, 0, 5, 1, 6, 2, 3);
		insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		assertSame(expectedIndex, Integer.valueOf(insertionIndex));

		left = Lists.newArrayList(4, 0, 5, 1, 6, 2, 7, 8, 3, 9);
		insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		assertSame(expectedIndex, Integer.valueOf(insertionIndex));
	}

	@Test
	public void insertionIndexTest4() {
		// Try and insert an element before the LCS, LCS being part of the second list
		// We'll add "0" in right and expect it to be added just before the LCS
		final Comparison emptyComparison = createEmptyComparison();

		List<Integer> left = Lists.newArrayList(0, 1, 2, 3);
		List<Integer> right = Lists.newArrayList(4, 1, 2, 3);
		// Start of LCS is 1
		Integer expectedIndex = Integer.valueOf(right.indexOf(Integer.valueOf(1)));
		int insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		assertSame(expectedIndex, Integer.valueOf(insertionIndex));

		left = Lists.newArrayList(0, 6, 1, 5, 2, 4, 3);
		right = Lists.newArrayList(7, 4, 1, 2, 3, 8);
		// Start of LCS is 1
		expectedIndex = Integer.valueOf(right.indexOf(Integer.valueOf(1)));
		insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		assertSame(expectedIndex, Integer.valueOf(insertionIndex));

		left = Lists.newArrayList(5, 0, 6, 7, 1, 2, 4, 3);
		right = Lists.newArrayList(7, 4, 1, 2, 9, 3, 8);
		// Start of LCS is 7
		expectedIndex = Integer.valueOf(right.indexOf(Integer.valueOf(7)));
		insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		assertSame(expectedIndex, Integer.valueOf(insertionIndex));
	}

	@Test
	public void insertionIndexTest5() {
		// Try and insert an element after the LCS, LCS being the whole second list
		final List<Integer> right = Lists.newArrayList(1, 2, 3);
		final Comparison emptyComparison = createEmptyComparison();
		// We'll add "0" in right and expect it to be added at the end
		final Integer expectedIndex = Integer.valueOf(right.size());

		List<Integer> left = Lists.newArrayList(1, 2, 3, 0);
		int insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		assertSame(expectedIndex, Integer.valueOf(insertionIndex));

		left = Lists.newArrayList(1, 2, 3, 4, 0);
		insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		assertSame(expectedIndex, Integer.valueOf(insertionIndex));

		left = Lists.newArrayList(1, 2, 3, 0, 4);
		insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		assertSame(expectedIndex, Integer.valueOf(insertionIndex));

		left = Lists.newArrayList(1, 2, 3, 5, 0, 4);
		insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		assertSame(expectedIndex, Integer.valueOf(insertionIndex));

		left = Lists.newArrayList(6, 1, 2, 3, 5, 0, 4);
		insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		assertSame(expectedIndex, Integer.valueOf(insertionIndex));

		left = Lists.newArrayList(1, 6, 2, 3, 5, 0, 4);
		insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		assertSame(expectedIndex, Integer.valueOf(insertionIndex));

		left = Lists.newArrayList(9, 1, 6, 2, 7, 8, 3, 5, 0, 4);
		insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		assertSame(expectedIndex, Integer.valueOf(insertionIndex));
	}

	@Test
	public void insertionIndexTest6() {
		// Try and insert an element after the LCS, LCS being part of the second list
		// We'll add "0" in right and expect it to be added just after the LCS
		final Comparison emptyComparison = createEmptyComparison();

		List<Integer> left = Lists.newArrayList(1, 2, 3, 0);
		List<Integer> right = Lists.newArrayList(1, 2, 3, 4);
		// End of LCS is 3
		Integer expectedIndex = Integer.valueOf(right.indexOf(Integer.valueOf(3)) + 1);
		int insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		assertSame(expectedIndex, Integer.valueOf(insertionIndex));

		left = Lists.newArrayList(1, 5, 2, 4, 3, 6, 0);
		right = Lists.newArrayList(8, 1, 2, 3, 4, 7);
		// End of LCS is 3
		expectedIndex = Integer.valueOf(right.indexOf(Integer.valueOf(3)) + 1);
		insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		assertSame(expectedIndex, Integer.valueOf(insertionIndex));

		left = Lists.newArrayList(1, 2, 4, 3, 7, 6, 0, 5);
		right = Lists.newArrayList(8, 1, 2, 9, 3, 4, 7);
		// End of LCS is 7
		expectedIndex = Integer.valueOf(right.indexOf(Integer.valueOf(7)) + 1);
		insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		assertSame(expectedIndex, Integer.valueOf(insertionIndex));
	}

	@Test
	public void insertionIndexTest7() {
		// Try and insert an element in the middle of the LCS, LCS being the whole second list
		// We'll add "0" in right and expect it to be added right after the closest LCS element
		final List<Integer> right = Lists.newArrayList(1, 2, 3);
		final Comparison emptyComparison = createEmptyComparison();

		List<Integer> left = Lists.newArrayList(1, 0, 2, 3);
		// Closest LCS element "before" is 1
		int expectedIndex = Integer.valueOf(right.indexOf(Integer.valueOf(1)) + 1);
		int insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		assertSame(expectedIndex, Integer.valueOf(insertionIndex));

		left = Lists.newArrayList(1, 2, 0, 3, 4);
		// Closest LCS element "before" is 2
		expectedIndex = Integer.valueOf(right.indexOf(Integer.valueOf(2)) + 1);
		insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		assertSame(expectedIndex, Integer.valueOf(insertionIndex));

		left = Lists.newArrayList(1, 0, 4, 2, 3);
		// Closest LCS element "before" is 1
		expectedIndex = Integer.valueOf(right.indexOf(Integer.valueOf(1)) + 1);
		insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		assertSame(expectedIndex, Integer.valueOf(insertionIndex));

		left = Lists.newArrayList(5, 1, 4, 2, 0, 3);
		// Closest LCS element "before" is 2
		expectedIndex = Integer.valueOf(right.indexOf(Integer.valueOf(2)) + 1);
		insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		assertSame(expectedIndex, Integer.valueOf(insertionIndex));

		left = Lists.newArrayList(6, 1, 7, 8, 0, 9, 2, 10, 3, 5, 4);
		// Closest LCS element "before" is 1
		expectedIndex = Integer.valueOf(right.indexOf(Integer.valueOf(1)) + 1);
		insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		assertSame(expectedIndex, Integer.valueOf(insertionIndex));
	}

	@Test
	public void insertionIndexTest8() {
		// Try and insert an element in the middle of the LCS, LCS being part of the second list
		// We'll add "0" in right and expect it to be added right after the closest LCS element
		final Comparison emptyComparison = createEmptyComparison();

		List<Integer> left = Lists.newArrayList(1, 2, 0, 3);
		List<Integer> right = Lists.newArrayList(1, 2, 3, 4);
		// Closest LCS element is 2
		Integer expectedIndex = Integer.valueOf(right.indexOf(Integer.valueOf(2)) + 1);
		int insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		assertSame(expectedIndex, Integer.valueOf(insertionIndex));

		left = Lists.newArrayList(1, 5, 2, 4, 0, 3, 6);
		right = Lists.newArrayList(8, 1, 2, 3, 4, 7);
		// Closest LCS element is 2
		expectedIndex = Integer.valueOf(right.indexOf(Integer.valueOf(2)) + 1);
		insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		assertSame(expectedIndex, Integer.valueOf(insertionIndex));

		/*
		 * This is documented in {@link DefaultDiffEngine#findInsertionIndex(Comparison, List, List, Object)}.
		 * Ensure the documentation stays in sync.
		 */
		left = Lists.newArrayList(1, 2, 4, 6, 8, 3, 0, 7, 5);
		right = Lists.newArrayList(8, 1, 2, 9, 3, 4, 7);
		// Closest LCS element is 3
		expectedIndex = Integer.valueOf(right.indexOf(Integer.valueOf(3)) + 1);
		insertionIndex = DiffUtil.findInsertionIndex(emptyComparison, left, right, Integer.valueOf(0));
		assertSame(expectedIndex, Integer.valueOf(insertionIndex));
	}

	/**
	 * Ensures that the two given lists contain the same elements in the same order. The kind of list does not
	 * matter.
	 * 
	 * @param list1
	 *            First of the two lists to compare.
	 * @param list2
	 *            Second of the two lists to compare.
	 */
	private static <T> void assertEqualContents(List<T> list1, List<T> list2) {
		final int size = list1.size();
		assertSame(Integer.valueOf(size), Integer.valueOf(list2.size()));

		for (int i = 0; i < size; i++) {
			assertEquals(list1.get(i), list2.get(i));
		}
	}

	/**
	 * Creates and return a new empty {@link Comparison} object with a defaut {@link EMFCompareConfiguration}.
	 * 
	 * @return the created {@link Comparison}.
	 */
	private static Comparison createEmptyComparison() {
		final Comparison emptyComparison = CompareFactory.eINSTANCE.createComparison();
		return emptyComparison;
	}
}
