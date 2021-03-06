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

package org.hibernate.shards.criteria;

import org.hibernate.Criteria;
import org.hibernate.impl.CriteriaImpl;

/**
 * Interface describing a {@link CriteriaImpl.Subcriteria}
 * that is shard-aware.  A ShardedSubcriteria must know how to provide
 * a reference to its parent {@link ShardedCriteria}.
 * @see CriteriaImpl.Subcriteria
 *
 * @author maxr@google.com (Max Ross)
 */
interface ShardedSubcriteria extends Criteria {

  /**
   * @return the owning ShardedCriteria
   */
  ShardedCriteria getParentCriteria();

}
