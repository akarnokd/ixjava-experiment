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

import java.util.Iterator;

import rx.functions.Func3;

final class IxZip3<T1, T2, T3, R> extends Ix<R> {
    final Iterable<T1> source1;

    final Iterable<T2> source2;

    final Iterable<T3> source3;

    final Func3<? super T1, ? super T2, ? super T3, ? extends R> zipper;
    
    public IxZip3(Iterable<T1> source1, Iterable<T2> source2, Iterable<T3> source3, 
            Func3<? super T1, ? super T2, ? super T3, ? extends R> zipper) {
        this.source1 = source1;
        this.source2 = source2;
        this.source3 = source3;
        this.zipper = zipper;
    }

    @Override
    public Iterator<R> iterator() {
        return new Zip3Iterator<T1, T2, T3, R>(source1.iterator(), source2.iterator(), 
                source3.iterator(), zipper);
    }

    static final class Zip3Iterator<T1, T2, T3, R> extends IxBaseIterator<R> {

        final Iterator<T1> source1;

        final Iterator<T2> source2;

        final Iterator<T3> source3;

        final Func3<? super T1, ? super T2, ? super T3, ? extends R> zipper;
        
        public Zip3Iterator(Iterator<T1> source1, Iterator<T2> source2, Iterator<T3> source3,
                Func3<? super T1, ? super T2, ? super T3, ? extends R> zipper) {
            this.source1 = source1;
            this.source2 = source2;
            this.source3 = source3;
            this.zipper = zipper;
        }
        
        @Override
        protected boolean moveNext() {
            if (!source1.hasNext()) {
                done = true;
                return false;
            }

            if (!source2.hasNext()) {
                done = true;
                return false;
            }

            if (!source3.hasNext()) {
                done = true;
                return false;
            }

            value = zipper.call(source1.next(), source2.next(), source3.next());
            hasValue = true;
            return true;
        }
        
    }
}
