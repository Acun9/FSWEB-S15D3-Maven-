package org.example;

import java.util.HashMap;
import java.util.Map;

public class WordCounter {

    public static Map<String, Integer> calculateWord(String text) {
        Map<String, Integer> counts = new HashMap<>();
        if (text == null || text.isEmpty()) {
            return counts;
        }

        // Küçük harfe çevir, noktalama işaretlerini temizle (noktalama yerine boşluk koy)
        String normalized = text.toLowerCase();
        normalized = normalized.replaceAll("[\\p{Punct}&&[^'-]]+", " ");
        // ayrıca özel tire ve uzun tire karakterlerini boşlukla değiştir
        normalized = normalized.replaceAll("[–—]", " ");

        String[] parts = normalized.split("\\s+");
        for (String p : parts) {
            String w = p.trim();
            if (w.isEmpty()) continue;
            counts.put(w, counts.getOrDefault(w, 0) + 1);
        }
        return counts;
    }

    public static Map<String, Integer> calculatedWord() {
        String text = "When the offensive resumed, the Turks received their first victory when the Greeks encountered stiff resistance in the battles of First and Second İnönü," +
                " due to İsmet Pasha's organization of an irregular militia into a regular army. " +
                " The two victories led to Allied proposals to amend the Treaty of Sèvres where both Ankara and Istanbul were represented, but Greece refused." +
                " With the conclusion of the Southern and Eastern fronts, Ankara was able to concentrate more forces on the West against the Greeks." +
                " They also began to receive support from Soviet Union, as well as France and Italy, who sought to check British influence in the Near East.\n" +
                " June–July 1921 saw heavy fighting in the Battle of Kütahya-Eskişehir. While it was an eventual Greek victory, the Turkish army withdrew in good order to the Sakarya river, their last line of defence." +
                " Mustafa Kemal Pasha replaced İsmet Pasha after the defeat as commander in chief as well as his political duties." +
                " The decision was made in the Greek military command to march on the nationalist capital of Ankara to force Mustafa Kemal to the negotiating table." +
                " For 21 days, the Turks and Greeks fought a pitched battle at the Sakarya river, which ended in Greek withdrawal." +
                " Almost of year of stalemate without much fighting followed, during which Greek moral and discipline faltered while Turkish strength increased." +
                " French and Italian forces evacuated from Anatolia. The Allies offered an armistice to the Turks, which Mustafa Kemal refused.";
        return calculateWord(text);
    }
}
