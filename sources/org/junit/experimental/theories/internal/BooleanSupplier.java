package org.junit.experimental.theories.internal;

import com.facebook.internal.ServerProtocol;
import java.util.Arrays;
import java.util.List;
import org.junit.experimental.theories.ParameterSignature;
import org.junit.experimental.theories.ParameterSupplier;
import org.junit.experimental.theories.PotentialAssignment;

public class BooleanSupplier extends ParameterSupplier {
    public List<PotentialAssignment> getValueSources(ParameterSignature parameterSignature) {
        return Arrays.asList(new PotentialAssignment[]{PotentialAssignment.forValue(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE, true), PotentialAssignment.forValue("false", false)});
    }
}
