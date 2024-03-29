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

import com.google.common.collect.testing.features.CollectionFeature;
import static com.google.common.collect.testing.features.CollectionFeature.KNOWN_ORDER;
import com.google.common.collect.testing.features.CollectionSize;
import static com.google.common.collect.testing.features.CollectionSize.ONE;
import static com.google.common.collect.testing.features.CollectionSize.SEVERAL;
import static com.google.common.collect.testing.features.CollectionSize.ZERO;

import java.util.NoSuchElementException;

/**
 * A generic JUnit test which tests {@code element()} operations on a queue.
 * Can't be invoked directly; please see
 * {@link com.google.common.collect.testing.CollectionTestSuiteBuilder}.
 *
 * @author Jared Levy
 */
public class QueueElementTester<E> extends AbstractQueueTester<E> {
  @CollectionSize.Require(ZERO)
  public void testElement_empty() {
    try {
      getQueue().element();
      fail("emptyQueue.element() should throw");
    } catch (NoSuchElementException expected) {}
    expectUnchanged();
  }

  @CollectionSize.Require(ONE)
  public void testElement_size1() {
    assertEquals("size1Queue.element() should return first element",
        samples.e0, getQueue().element());
    expectUnchanged();
  }

  @CollectionFeature.Require(KNOWN_ORDER)
  @CollectionSize.Require(SEVERAL)
  public void testElement_sizeMany() {
    assertEquals("sizeManyQueue.element() should return first element",
        samples.e0, getQueue().element());
    expectUnchanged();
  }
}
