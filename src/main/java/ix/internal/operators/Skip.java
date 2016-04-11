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

package ix.internal.operators;

import java.util.*;

public final class Skip<T> implements Iterable<T> {
    final Iterable<? extends T> source;
    
    final int num;

    public Skip(Iterable<? extends T> source, int num) {
        this.source = source;
        this.num = num;
    }
    
    @Override
    public Iterator<T> iterator() {
        @SuppressWarnings("unchecked")
        Iterator<T> it = (Iterator<T>)source.iterator();
        
        int i = num;
        
        while (i > 0) {
            if (!it.hasNext()) {
                return Collections.<T>emptyList().iterator();
            }
            it.next();
            i--;
        }
        
        return it;
    }
}