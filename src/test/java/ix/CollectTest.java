/*
 * Copyright 2011-2016 David Karnok
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ix;

import org.junit.Test;

import rx.functions.*;

import static org.junit.Assert.*;

public class CollectTest {

    @Test
    public void normal() {
        Ix<Integer[]> source = Ix.range(1, 10).collect(new Func0<Integer[]>() {
            @Override
            public Integer[] call() {
                return new Integer[] { 0 };
            }
        }, new Action2<Integer[], Integer>() {
            @Override
            public void call(Integer[] a, Integer b) {
                a[0] += b;
            }
        });
        
        assertEquals(55, source.first()[0].intValue());
    }
}
