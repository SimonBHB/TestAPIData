package fr.simonbhb.animatedmagic.Utils.MyKeys;

import com.flowpowered.math.vector.Vector3d;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.manipulator.DataManipulatorBuilder;
import org.spongepowered.api.data.manipulator.immutable.common.AbstractImmutableSingleData;
import org.spongepowered.api.data.manipulator.mutable.common.AbstractSingleData;
import org.spongepowered.api.data.merge.MergeFunction;
import org.spongepowered.api.data.persistence.AbstractDataBuilder;
import org.spongepowered.api.data.persistence.InvalidDataException;
import org.spongepowered.api.data.value.BaseValue;
import org.spongepowered.api.data.value.immutable.ImmutableValue;
import org.spongepowered.api.data.value.mutable.Value;

import java.util.Optional;

// Example of singular data
// Example of single-class layout
// Example of non-persistent data
public class MyVector3dData extends AbstractSingleData<Vector3d, MyVector3dData, MyVector3dData.Immutable> {

    public MyVector3dData(Vector3d value, Key<? extends Value<Vector3d>> usedKey) {
        super(usedKey, value);
    }

    @Override
    public Value<Vector3d> getValueGetter() {
        return Sponge.getRegistry().getValueFactory().createValue(ToolKeys.VECTOR3D, getValue());
    }

    public Value<Vector3d> blockstate() {
        return getValueGetter();
    }

    @Override
    public Optional<MyVector3dData> fill(DataHolder dataHolder, MergeFunction overlap) {
        Optional<MyVector3dData> data_ = dataHolder.get(MyVector3dData.class);
        if (data_.isPresent()) {
            MyVector3dData data = data_.get();
            MyVector3dData finalData = overlap.merge(this, data);
            setValue(finalData.getValue());
        }
        return Optional.of(this);
    }

    @Override
    public Optional<MyVector3dData> from(DataContainer container) {
        return Optional.of(this);
    }

    @Override
    public MyVector3dData copy() {
        return new MyVector3dData(getValue(), ToolKeys.VECTOR3D);
    }

    @Override
    public Immutable asImmutable() {
        return new Immutable(ToolKeys.VECTOR3D, getValue());
    }

    @Override
    public int getContentVersion() {
        return 1;
    }

    public class Immutable extends AbstractImmutableSingleData<Vector3d, Immutable, MyVector3dData> {

        public Immutable(Key<? extends Value<Vector3d>> usedKey, Vector3d value) {
            super(usedKey, value);
        }

        @Override
        public <E> Optional<Immutable> with(Key<? extends BaseValue<E>> key, E value) {
            if(this.supports(key)) {
                return Optional.of(asMutable().set(key, value).asImmutable());
            } else {
                return Optional.empty();
            }
        }

        @Override
        protected ImmutableValue<?> getValueGetter() {
            return Sponge.getRegistry().getValueFactory().createValue(ToolKeys.VECTOR3D, getValue()).asImmutable();
        }

        @Override
        public MyVector3dData asMutable() {
            return new MyVector3dData(getValue(), ToolKeys.VECTOR3D);
        }

        @Override
        public int getContentVersion() {
            return 1;
        }
    }
    public static class Builder extends AbstractDataBuilder<MyVector3dData> implements DataManipulatorBuilder<MyVector3dData, Immutable> {
        public Builder() {
            super(MyVector3dData.class, 1);
        }

        @Override
        public MyVector3dData create() {
            return new MyVector3dData(null, ToolKeys.VECTOR3D);
        }

        @Override
        public Optional<MyVector3dData> createFrom(DataHolder dataHolder) {
            return create().fill(dataHolder);
        }

        @Override
        protected Optional<MyVector3dData> buildContent(DataView container) throws InvalidDataException {
            return Optional.of(create());
        }
    }
}
