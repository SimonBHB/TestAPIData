package fr.simonbhb.animatedmagic.Utils.MyKeys;

import com.flowpowered.math.vector.Vector3d;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.manipulator.mutable.common.AbstractData;
import org.spongepowered.api.data.merge.MergeFunction;
import org.spongepowered.api.data.value.mutable.Value;

import java.util.Optional;
import java.util.UUID;

public class MyStandardData extends AbstractData<MyStandardData, MyImmutableStandardData> {
    private UUID id;
    private String name;

    private Vector3d vector3d;

    public MyStandardData(UUID id, String name, Vector3d vector3d) {
        this.id = id;
        this.name = name;

        this.vector3d = vector3d;
        // you must call this!
        registerGettersAndSetters();
    }

    @Override
    protected void registerGettersAndSetters() {
        registerFieldGetter(ToolKeys.STANDARD_ID, () -> this.id);
        registerFieldSetter(ToolKeys.STANDARD_ID, id -> this.id = id);
        registerKeyValue(ToolKeys.STANDARD_ID, this::id);

        registerFieldGetter(ToolKeys.STANDARD_NAME, () -> this.name);
        registerFieldSetter(ToolKeys.STANDARD_NAME, name -> this.name = name);
        registerKeyValue(ToolKeys.STANDARD_NAME, this::name);

        registerFieldGetter(ToolKeys.VECTOR3D, () -> this.vector3d);
        registerFieldSetter(ToolKeys.VECTOR3D, vector3d -> this.vector3d = vector3d);
        registerKeyValue(ToolKeys.VECTOR3D, this::vector3d);
    }

    public Value<UUID> id() {
        return Sponge.getRegistry().getValueFactory().createValue(ToolKeys.STANDARD_ID, id);
    }

    public Value<String> name() {
        return Sponge.getRegistry().getValueFactory().createValue(ToolKeys.STANDARD_NAME, name);
    }

    public Value<Vector3d> vector3d() {
        return Sponge.getRegistry().getValueFactory().createValue(ToolKeys.VECTOR3D, vector3d);
    }

    @Override
    public Optional<MyStandardData> fill(DataHolder dataHolder, MergeFunction overlap) {
        Optional<MyStandardData> otherData_ = dataHolder.get(MyStandardData.class);
        if (otherData_.isPresent()) {
            MyStandardData otherData = otherData_.get();
            MyStandardData finalData = overlap.merge(this, otherData);
            this.id = finalData.id;
            this.name = finalData.name;
            this.vector3d = finalData.vector3d;
        }
        return Optional.of(this);
    }

    // the double method isn't strictly necessary but makes implementing the builder easier
    @Override
    public Optional<MyStandardData> from(DataContainer container) {
        return from((DataView) container);
    }

    public Optional<MyStandardData> from(DataView view) {
        if (view.contains(ToolKeys.VECTOR3D.getQuery())) {
            this.id = view.getObject(ToolKeys.STANDARD_ID.getQuery(), UUID.class).get();
            this.name = view.getObject(ToolKeys.STANDARD_NAME.getQuery(), String.class).get();
            this.vector3d = view.getObject(ToolKeys.VECTOR3D.getQuery(), Vector3d.class).get();
            return Optional.of(this);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public MyStandardData copy() {
        return new MyStandardData(this.id, this.name, this.vector3d);
    }

    @Override
    public MyImmutableStandardData asImmutable() {
        return new MyImmutableStandardData(this.id, this.name, this.vector3d);
    }

    @Override
    public int getContentVersion() {
        return 1;
    }

    // IMPORTANT this is what causes your data to be written to NBT
    @Override
    public DataContainer toContainer() {
        return super.toContainer()
                .set(ToolKeys.STANDARD_ID.getQuery(), this.id)
                .set(ToolKeys.STANDARD_NAME.getQuery(), this.name)
                .set(ToolKeys.VECTOR3D.getQuery(), this.vector3d);
    }
}
