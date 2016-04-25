package main.OreGenUltimate;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class Listeners implements Listener {
    private final OreGenUltimate _ogu;

    public Listeners(OreGenUltimate ogu) {
        _ogu = ogu;
        _ogu.getServer().getPluginManager().registerEvents(this, ogu);
    }

    @EventHandler
    public void onFromTo(BlockFromToEvent event) {
        int id = event.getBlock().getTypeId();
        if (id >= 8 && id <= 11) {
            Block b = event.getToBlock();
            int toid = b.getTypeId();
            if (toid == 0) {
                if (generatesCobble(id, b)) {
                    List<String> worlds = _ogu.getConfig().getStringList("Worlds");
                    if (worlds.contains(event.getBlock().getLocation().getWorld().getName())){
                        //Create a HashMap for our minerals
                        Map<String, Integer> minerals = new HashMap<>();
                        //Coal Blocks
                        minerals.put("coal", _ogu.getConfig().getInt("Chances.Coal"));
                        minerals.put("coalblock", _ogu.getConfig().getInt("Chances.CoalBlock"));
                        //Iron Blocks
                        minerals.put("iron", _ogu.getConfig().getInt("Chances.Iron"));
                        minerals.put("ironblock", _ogu.getConfig().getInt("Chances.IronBlock"));
                        //Gold Blocks
                        minerals.put("gold", _ogu.getConfig().getInt("Chances.Gold"));
                        minerals.put("goldblock", _ogu.getConfig().getInt("Chances.GoldBlock"));
                        //Redstone Blocks
                        minerals.put("redstone", _ogu.getConfig().getInt("Chances.Redstone"));
                        minerals.put("redstoneblock", _ogu.getConfig().getInt("Chances.RedstoneBlock"));
                        //Lapis Blocks
                        minerals.put("lapis", _ogu.getConfig().getInt("Chances.Lapis"));
                        minerals.put("lapisblock", _ogu.getConfig().getInt("Chances.LapisBlock"));
                        //Emerald Blocks
                        minerals.put("emerald", _ogu.getConfig().getInt("Chances.Emerald"));
                        minerals.put("emeraldblock", _ogu.getConfig().getInt("Chances.EmeraldBlock"));
                        //Diamond Blocks
                        minerals.put("diamond", _ogu.getConfig().getInt("Chances.Diamond"));
                        minerals.put("diamondblock", _ogu.getConfig().getInt("Chances.DiamondBlock"));

                        //Sort our HashMap from smallest to largest
                        Map<String, Integer> sortedMinerals = sortByValue(minerals);
                        Map<String, Integer> sortedMinerals2 = sortByValue(minerals);
                        
                        //Our chance from config file
                        int chance = _ogu.getConfig().getInt("Chance");
                        //Set the initial value of Calculation Number
                        int calcNum = 0;
                        //Create and iterator
                        Iterator calcIt = sortedMinerals.entrySet().iterator();
                        //Iterate through
                        while(calcIt.hasNext()){
                            Map.Entry calcPairs = (Map.Entry)calcIt.next();
                            //Increment our calcNum
                            calcNum = calcNum + (int) calcPairs.getValue();
                            //Change our value
                            sortedMinerals2.put((String) calcPairs.getKey(), calcNum);
                            //Change our chance
                            chance = chance + calcNum;
                            calcIt.remove(); // avoids a ConcurrentModificationException
                        }
                        Random pick = new Random();
                        //Create a random number
                        int n = pick.nextInt(chance);
                        //Now lets iterate through our HashMap
                        Iterator it = sortedMinerals2.entrySet().iterator();
                        while(it.hasNext()){
                            Map.Entry pairs = (Map.Entry)it.next();
                            if(n <= (int) pairs.getValue()){
                                if(pairs.getKey() == "coal") {
                                    b.setType(Material.COAL_ORE);
                                    if(_ogu.getConfig().getInt("debug") == 1){
                                        _ogu.getServer().broadcastMessage("We generated Coal");
                                    }
                                }else if(pairs.getKey() == "coalblock") {
                                    b.setType(Material.COAL_BLOCK);
                                    if(_ogu.getConfig().getInt("debug") == 1){
                                        _ogu.getServer().broadcastMessage("We generated Coal Block");
                                    }
                                }else if(pairs.getKey() == "iron") {
                                    b.setType(Material.IRON_ORE);
                                    if(_ogu.getConfig().getInt("debug") == 1){
                                        _ogu.getServer().broadcastMessage("We generated Iron");
                                    }
                                }else if(pairs.getKey() == "ironblock") {
                                    b.setType(Material.IRON_BLOCK);
                                    if(_ogu.getConfig().getInt("debug") == 1){
                                        _ogu.getServer().broadcastMessage("We generated Iron Block");
                                    }
                                }else if(pairs.getKey() == "gold") {
                                    b.setType(Material.GOLD_ORE);
                                    if(_ogu.getConfig().getInt("debug") == 1){
                                        _ogu.getServer().broadcastMessage("We generated Gold");
                                    }
                                }else if(pairs.getKey() == "goldblock") {
                                    b.setType(Material.GOLD_BLOCK);
                                    if(_ogu.getConfig().getInt("debug") == 1){
                                        _ogu.getServer().broadcastMessage("We generated Gold Block");
                                    }
                                }else if(pairs.getKey() == "redstone") {
                                    b.setType(Material.REDSTONE_ORE);
                                    if(_ogu.getConfig().getInt("debug") == 1){
                                        _ogu.getServer().broadcastMessage("We generated Redstone");
                                    }
                                }else if(pairs.getKey() == "redstoneblock") {
                                    b.setType(Material.REDSTONE_BLOCK);
                                    if(_ogu.getConfig().getInt("debug") == 1){
                                        _ogu.getServer().broadcastMessage("We generated Redstone Block");
                                    }
                                }else if(pairs.getKey() == "lapis") {
                                    b.setType(Material.LAPIS_ORE);
                                    if(_ogu.getConfig().getInt("debug") == 1){
                                        _ogu.getServer().broadcastMessage("We generated Lapis");
                                    }
                                }else if(pairs.getKey() == "lapisblock") {
                                    b.setType(Material.LAPIS_BLOCK);
                                    if(_ogu.getConfig().getInt("debug") == 1){
                                        _ogu.getServer().broadcastMessage("We generated Lapis Block");
                                    }
                                }else if(pairs.getKey() == "emerald") {
                                    b.setType(Material.EMERALD_ORE);
                                    if(_ogu.getConfig().getInt("debug") == 1){
                                        _ogu.getServer().broadcastMessage("We generated Emerald");
                                    }
                                }else if(pairs.getKey() == "emeraldblock") {
                                    b.setType(Material.EMERALD_BLOCK);
                                    if(_ogu.getConfig().getInt("debug") == 1){
                                        _ogu.getServer().broadcastMessage("We generated Emerald Block");
                                    }
                                }else if(pairs.getKey() == "diamond") {
                                    b.setType(Material.DIAMOND_ORE);
                                    if(_ogu.getConfig().getInt("debug") == 1){
                                        _ogu.getServer().broadcastMessage("We generated Diamond");
                                    }
                                }else if(pairs.getKey() == "diamondblock") {
                                    b.setType(Material.DIAMOND_BLOCK);
                                    if(_ogu.getConfig().getInt("debug") == 1){
                                        _ogu.getServer().broadcastMessage("We generated Diamond Block");
                                    }
                                }else{
                                    if(_ogu.getConfig().getInt("debug") == 1){
                                        _ogu.getServer().broadcastMessage("We generated Cobblestone");
                                    }
                                }
                                break;
                            }
                            it.remove(); // avoids a ConcurrentModificationException
                        }
                    }
                }
            }
        }
    }

    private final BlockFace[] faces = new BlockFace[] { BlockFace.SELF, BlockFace.UP, BlockFace.DOWN, BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST };

    public boolean generatesCobble(int id, Block b) {
            int mirrorID1 = (id == 8 || id == 9 ? 10 : 8);
            int mirrorID2 = (id == 8 || id == 9 ? 11 : 9);
            for (BlockFace face : faces) {
                    Block r = b.getRelative(face, 1);
                    if (r.getTypeId() == mirrorID1 || r.getTypeId() == mirrorID2) {
                            return true;
                    }
            }
            return false;
    }
    
    //sortByValue function
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue( Map<K, V> map ){
        List<Map.Entry<K, V>> list = new LinkedList<>( map.entrySet() );
        Collections.sort( list, new Comparator<Map.Entry<K, V>>(){
            @Override
            public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 ){
                return (o1.getValue()).compareTo( o2.getValue() );
            }
        });

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list){
            result.put( entry.getKey(), entry.getValue() );
        }
        return result;
    }
}