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

import rx.functions.Func0;

public class DeferTest {

    @Test
    public void normal() {
        Ix<Integer> source = Ix.defer(new Func0<Iterable<Integer>>() {
            int count;
            @Override
            public Iterable<Integer> call() {
                return Ix.range(++count, 2);
            }
        });
        
        IxTestHelper.assertValues(source, 1, 2);
        IxTestHelper.assertValues(source, 2, 3);
        IxTestHelper.assertValues(source, 3, 4);
    }
}
