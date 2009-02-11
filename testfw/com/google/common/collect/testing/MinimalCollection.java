/*
 * Copyright (C) 2007 Google Inc.
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

package com.google.common.collect.testing;

import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * A simplistic collection which implements only the bare minimum allowed by the
 * spec, and throws exceptions whenever it can.
 *
 * @author Kevin Bourrillion
 */
public class MinimalCollection<E> extends AbstractCollection<E> {
  public static <E> MinimalCollection<E> of(E... contents) {
    return new MinimalCollection<E>(Object.class, contents);
  }

  // TODO: use this
  public static <E> MinimalCollection<E> ofClassAndContents(
      Class<? super E> type, E... contents) {
    return new MinimalCollection<E>(type, contents);
  }

  private final E[] contents;
  private final Class<? super E> type;

  private MinimalCollection(Class<? super E> type, E... contents) {
    this.contents = contents.clone();
    this.type = type;
  }

  @Override public int size() {
    return contents.length;
  }

  @Override public boolean contains(Object object) {
    Helpers.checkNotNull(object); // behave badly
    type.cast(object); // behave badly
    return Arrays.asList(contents).contains(object);
  }

  @Override public boolean containsAll(Collection<?> collection) {
    for (Object object : collection) {
      Helpers.checkNotNull(object); // behave badly
    }
    return super.containsAll(collection);
  }

  @Override public Iterator<E> iterator() {
    return Arrays.asList(contents).iterator();
  }

  @Override public Object[] toArray() {
    return contents.clone();
  }

  /*
   * a "type A" unmodifiable collection freaks out proactively, even if there
   * wasn't going to be any actual work to do anyway
   */
  
  @Override public boolean addAll(Collection<? extends E> elementsToAdd) {
    throw up();
  }
  @Override public boolean removeAll(Collection<?> elementsToRemove) {
    throw up();
  }
  @Override public boolean retainAll(Collection<?> elementsToRetain) {
    throw up();
  }
  @Override public void clear() {
    throw up();
  }
  private static UnsupportedOperationException up() {
    throw new UnsupportedOperationException();
  }
}