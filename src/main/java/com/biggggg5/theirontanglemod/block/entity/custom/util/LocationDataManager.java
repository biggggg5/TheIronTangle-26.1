package com.biggggg5.theirontanglemod.block.entity.custom.util;

import com.biggggg5.theirontanglemod.TheIronTangleMod;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;

import java.util.*;

public class LocationDataManager extends SavedData {
    public static final Codec<LocationDataManager> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            LocationData.CODEC.listOf().fieldOf("instances").forGetter(sd -> sd.instances.stream().toList())
    ).apply(instance, LocationDataManager::new));

    public static final SavedDataType<LocationDataManager> ID = new SavedDataType<>(
            Identifier.fromNamespaceAndPath(TheIronTangleMod.MOD_ID, "location_data_instances"),
            LocationDataManager::new,
            CODEC
    );

    public final Set<LocationData> instances;

    public LocationDataManager()
    {
        instances = new HashSet<>();
    }

    public LocationDataManager(List<LocationData> instances)
    {
        this.instances = new HashSet<>(instances);
    }

    public static LocationDataManager getSavedData(MinecraftServer server)
    {
        return server.getDataStorage().computeIfAbsent(ID);
    }



    public void addLocationData(MinecraftServer server, LocationData locationData) {
        LocationDataManager saved = getSavedData(server);

        boolean exists = saved.instances.stream()
                .anyMatch(locationDataUsed -> locationDataUsed.name != null
                        && locationDataUsed.name.equals(locationData.name)
                        && locationDataUsed.pos.equals(locationData.pos)
                        && locationDataUsed.dimension.equals(locationData.dimension));

        if (!exists) {
            saved.instances.add(locationData);
            saved.setDirty();
        }
    }

    public void updateLocationData(MinecraftServer server, LocationData locationData) {
        LocationDataManager saved = getSavedData(server);


        setDirty();
    }
//
//    public void removeLocationData(LocationData locationData) {
//        LocationDataIndexManager.locationDataRemoved(locationData);
//        store.removeLocationData(locationData);
//        setDirty();
//    }
//
//    public Optional<LocationData> getLocationDataAt(BlockGetter world, BlockPos pos) {
//        return store.getLocationDataAt(world, pos);
//    }
//
//    public Optional<LocationData> getLocationDataById(UUID locationDataUid) {
//        return store.getLocationDataById(locationDataUid);
//    }
//
//    public Optional<LocationData> findLocationDataByName(String name) {
//        return store.findLocationDataByName(name);
//    }
//
    public List<LocationData> getLocationDataList(MinecraftServer server) {
        LocationDataManager saved = getSavedData(server);

        return saved.instances.stream().toList();
    }

}
