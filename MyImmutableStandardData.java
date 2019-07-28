import com.flowpowered.math.vector.Vector3d;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.manipulator.immutable.common.AbstractImmutableData;
import org.spongepowered.api.data.value.immutable.ImmutableValue;

import java.util.UUID;

public class MyImmutableStandardData extends AbstractImmutableData<MyImmutableStandardData, MyStandardData> {
    private UUID id;
    private String name;

    private Vector3d vector3d;

    public MyImmutableStandardData(UUID id, String name, Vector3d vector3d) {
        this.id = id;
        this.name = name;

        this.vector3d = vector3d;
        registerGetters();
    }

    @Override
    protected void registerGetters() {
        registerFieldGetter(ToolKeys.STANDARD_ID, () -> this.id);
        registerFieldGetter(ToolKeys.STANDARD_NAME, () -> this.name);
        registerFieldGetter(ToolKeys.VECTOR3D, () -> this.vector3d);

        registerKeyValue(ToolKeys.STANDARD_ID, this::id);
        registerKeyValue(ToolKeys.STANDARD_NAME, this::name);
        registerKeyValue(ToolKeys.VECTOR3D, this::vector3d);
    }

    public ImmutableValue<UUID> id() {
        return Sponge.getRegistry().getValueFactory().createValue(ToolKeys.STANDARD_ID, id).asImmutable();
    }

    public ImmutableValue<String> name() {
        return Sponge.getRegistry().getValueFactory().createValue(ToolKeys.STANDARD_NAME, name).asImmutable();
    }

    public ImmutableValue<Vector3d> vector3d() {
        return Sponge.getRegistry().getValueFactory().createValue(ToolKeys.VECTOR3D, vector3d).asImmutable();
    }

    @Override
    public MyStandardData asMutable() {
        return new MyStandardData(id, name, vector3d);
    }

    @Override
    public int getContentVersion() {
        return 1;
    }

    @Override
    public DataContainer toContainer() {
        return super.toContainer()
//                .set(ToolKeys.VECTOR3D.getQuery(), this)
                .set(ToolKeys.STANDARD_ID.getQuery(), this.id)
                .set(ToolKeys.STANDARD_NAME.getQuery(), this.name)
                .set(ToolKeys.VECTOR3D.getQuery(), this.vector3d);
    }
}
