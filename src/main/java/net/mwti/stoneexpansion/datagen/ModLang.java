package net.mwti.stoneexpansion.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import net.mwti.stoneexpansion.block.BlockMaterial;
import net.mwti.stoneexpansion.block.BlockShape;
import net.mwti.stoneexpansion.block.BlockVariant;
import net.mwti.stoneexpansion.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

import static net.mwti.stoneexpansion.StoneExpansion.MOD_ID;

public class ModLang extends FabricLanguageProvider {
    public ModLang(FabricDataOutput dataGenerator, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataGenerator, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup w, TranslationBuilder translationBuilder) {

        translationBuilder.add("itemGroup." + MOD_ID + ".blocks","Mwti's Stone Expansion");

        for (BlockMaterial material : BlockMaterial.values()) {
            for(BlockVariant variant : BlockVariant.values()) {
                for(BlockShape shape : BlockShape.values()) {
                    ModBlocks.getModdedBlock(material, variant, shape).ifPresent(block ->
                        translationBuilder.add(block, translateKeyToEnglish("block." + MOD_ID + ".", block.getTranslationKey()))
                    );
                }
            }
        }
    }

    private static String translateKeyToEnglish(String prefix, String key){
        String[] parts = key.substring(prefix.length()).split("_");
        StringBuilder result = new StringBuilder();

        for (String part : parts) {
            String capitalizedPart = part.substring(0, 1).toUpperCase() + part.substring(1);
            result.append(capitalizedPart).append(" ");
        }
        return result.toString().trim();
    }
}