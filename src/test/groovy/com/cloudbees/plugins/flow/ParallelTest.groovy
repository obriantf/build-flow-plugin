/*
 * Copyright (C) 2011 CloudBees Inc.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * along with this program; if not, see <http://www.gnu.org/licenses/>.
 */

package com.cloudbees.plugins.flow

import static hudson.model.Result.SUCCESS

class ParallelTest extends DSLTestCase {

    public void testParallel() {
        def jobs = createJobs(["job1", "job2", "job3", "job4"])
        def flow = run("""
            parallel {
                build("job1")
                build("job2")
                build("job3")
            }
            build("job4")
        """)
        assertAllSuccess(jobs)
        assert SUCCESS == flow.result
        println flow.builds.edgeSet()
    }


}