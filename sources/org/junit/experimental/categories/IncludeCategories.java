package org.junit.experimental.categories;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.experimental.categories.Categories;
import org.junit.runner.manipulation.Filter;

public final class IncludeCategories extends CategoryFilterFactory {
    /* access modifiers changed from: protected */
    public Filter createFilter(List<Class<?>> list) {
        return new IncludesAny(list);
    }

    private static class IncludesAny extends Categories.CategoryFilter {
        public IncludesAny(List<Class<?>> list) {
            this((Set<Class<?>>) new HashSet(list));
        }

        public IncludesAny(Set<Class<?>> set) {
            super(true, set, true, (Set<Class<?>>) null);
        }

        public String describe() {
            return "includes " + super.describe();
        }
    }
}
