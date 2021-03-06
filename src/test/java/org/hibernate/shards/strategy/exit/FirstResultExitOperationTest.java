/**
 * Copyright (C) 2007 Google Inc.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.

 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.

 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA
 */

package org.hibernate.shards.strategy.exit;


import junit.framework.TestCase;

import org.hibernate.shards.util.Lists;

import java.util.List;

/**
 * @author Maulik Shah
 */
public class FirstResultExitOperationTest extends TestCase {

  public void testApply() {
    FirstResultExitOperation exitOp = new FirstResultExitOperation(1);

    List<Object> list = Lists.<Object>newArrayList(1, 2, null, 3, 4, 5);

    List<Object> objects = exitOp.apply(list);
    assertEquals(4, objects.size());
    assertNoNullElements(objects);
    assertEquals(Lists.newArrayList(2, 3, 4, 5), objects);
    exitOp = new FirstResultExitOperation(2);

    list = Lists.<Object>newArrayList(1, 2, null, 3, 4, 5);

    objects = exitOp.apply(list);
    assertEquals(3, objects.size());
    assertNoNullElements(objects);
    assertEquals(Lists.newArrayList(3, 4, 5), objects);
  }

  public void testApplyWhenNoResults() {
    FirstResultExitOperation exitOp = new FirstResultExitOperation(9);

    List<Object> list = Lists.newArrayList();

    List<Object> objects = exitOp.apply(list);
    assertTrue(objects.isEmpty());

    Object nullObj = null;
    list = Lists.newArrayList(nullObj, nullObj, nullObj);

    objects = exitOp.apply(list);
    assertTrue(objects.isEmpty());
  }

  public void testApplyWhenFirstResultIsTooBig() {
    FirstResultExitOperation exitOp = new FirstResultExitOperation(9);

    List<Object> list = Lists.<Object>newArrayList(1, 2, null, 3, 4, 5);

    List<Object> objects = exitOp.apply(list);
    assertTrue(objects.isEmpty());

    // edge case
    exitOp = new FirstResultExitOperation(list.size());
    objects = exitOp.apply(list);
    assertTrue(objects.isEmpty());
  }

  private void assertNoNullElements(List<Object> objects) {
    for(Object obj : objects) {
      assertTrue(obj != null);
    }
  }
}
