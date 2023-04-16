package org.junit.experimental.categories;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.experimental.categories.Categories;
import org.junit.runner.manipulation.Filter;

public final class ExcludeCategories extends CategoryFilterFactory {
    /* access modifiers changed from: protected */
    public Filter createFilter(List<Class<?>> list) {
        return new ExcludesAny(list);
    }

    private static class ExcludesAny extends Categories.CategoryFilter {
        public ExcludesAny(List<Class<?>> list) {
            this((Set<Class<?>>) new HashSet(list));
        }

        public ExcludesAny(Set<Class<?>> set) {
            super(true, (Set<Class<?>>) null, true, set);
        }

        public String describe() {
            return "excludes " + super.describe();
        }
    }
}
