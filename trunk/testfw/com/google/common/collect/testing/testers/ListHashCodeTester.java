/*
 * Copyright (C) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.common.collect.testing.testers;

/**
 * Tests {@link java.util.List#hashCode}.
 *
 * @author George van den Driessche
 */
public class ListHashCodeTester<E> extends AbstractListTester<E> {
  public void testHashCode() {
    int expectedHashCode = 1;
    for (E element : getSampleElements()) {
      expectedHashCode = 31 * expectedHashCode +
          ((element == null) ? 0 : element.hashCode());
    }
    assertEquals(
        "A List's hashCode() should be computed from those of its elements.",
        expectedHashCode, getList().hashCode());
  }
}