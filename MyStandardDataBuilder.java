import com.flowpowered.math.vector.Vector3d;
import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.manipulator.DataManipulatorBuilder;
import org.spongepowered.api.data.persistence.AbstractDataBuilder;
import org.spongepowered.api.data.persistence.InvalidDataException;

import java.util.Optional;
import java.util.UUID;

public class MyStandardDataBuilder extends AbstractDataBuilder<MyStandardData> implements DataManipulatorBuilder<MyStandardData, MyImmutableStandardData> {
    public MyStandardDataBuilder() {
        super(MyStandardData.class, 1);
    }

    @Override
    public MyStandardData create() {
        return new MyStandardData(UUID.randomUUID(), "", new Vector3d(0,0,0));
    }

    @Override
    public Optional<MyStandardData> createFrom(DataHolder dataHolder) {
        return create().fill(dataHolder);
    }

    @Override
    protected Optional<MyStandardData> buildContent(DataView container) throws InvalidDataException {
        return create().from(container);
    }
}
