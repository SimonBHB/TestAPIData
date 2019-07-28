import com.flowpowered.math.vector.Vector3d;
import com.google.common.reflect.TypeToken;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.value.mutable.Value;
import org.spongepowered.api.util.TypeTokens;
import org.spongepowered.api.world.biome.BiomeType;

import java.util.UUID;

public class ToolKeys {
    public ToolKeys() {}
//    public static void dummy() {} // invoke static constructor

    public static final Key<Value<UUID>> STANDARD_ID;
    public static final Key<Value<String>> STANDARD_NAME;

    public static final Key<Value<Vector3d>> VECTOR3D;

    static {

        STANDARD_ID = Key.builder()
                .type(TypeTokens.UUID_VALUE_TOKEN)
                .id("standard_id")
                .name("Standard ID")
                .query(DataQuery.of('.', "standard.name"))
                .build();
        STANDARD_NAME = Key.builder()
                .type(TypeTokens.STRING_VALUE_TOKEN)
                .id("standard_name")
                .name("Standard Name")
                .query(DataQuery.of('.', "standard.name"))
                .build();


        VECTOR3D = Key.builder()
                .type(TypeTokens.VECTOR_3D_VALUE_TOKEN)
                .id("vector3d_id")
                .name("Vector3d ID")
                .query(DataQuery.of('.', "vector3d.name"))
                .build();

    }

}
