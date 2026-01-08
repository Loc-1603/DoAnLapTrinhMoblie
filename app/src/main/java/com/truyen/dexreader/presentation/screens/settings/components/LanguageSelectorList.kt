package com.truyen.dexreader.presentation.screens.settings.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.truyen.dexreader.R
import com.truyen.dexreader.domain.model.LanguageType

@Composable
fun LanguageSelectorList(
    selectedLanguageType: LanguageType,
    onSelectedLanguage: (LanguageType) -> Unit,
    modifier: Modifier = Modifier
) {
    // Đồng bộ cấu trúc Card: Shape và Elevation giống ThemeSelector
    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = modifier
    ) {
        // Sử dụng LazyColumn và Width(165.dp) để có độ rộng bằng bảng trên
        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(24.dp)
                .width(165.dp) // Chỉ số quan trọng để 2 bảng bằng nhau
        ) {
            item {
                // Đồng bộ Style chữ tiêu đề: ExtraBold và Căn giữa
                Text(
                    text = stringResource(R.string.language_options),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                )
            }

            // Duyệt danh sách ngôn ngữ
            items(LanguageType.entries.toTypedArray()) { languageType ->
                // Ở đây mình giả định bạn đã có hoặc sẽ tạo LanguageSelectorItem
                // để có UI đồng bộ với ThemeSelectorItem (nút bấm hình tròn hoặc Row)
                LanguageSelectorItem(
                    isSelected = selectedLanguageType == languageType,
                    languageType = languageType,
                    onSelectedLanguage = onSelectedLanguage,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun LanguageSelectorItem(
    isSelected: Boolean,
    languageType: LanguageType,
    onSelectedLanguage: (LanguageType) -> Unit,
    modifier: Modifier = Modifier
) {
    // Tận dụng Surface hoặc Row để tạo hiệu ứng click giống ThemeSelectorItem
    Surface(
        onClick = { onSelectedLanguage(languageType) },
        shape = MaterialTheme.shapes.small,
        color = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface,
        modifier = modifier.padding(vertical = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(8.dp)
        ) {
            RadioButton(
                selected = isSelected,
                onClick = null // Để Surface xử lý click
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = when (languageType) {
                    LanguageType.ENGLISH -> stringResource(R.string.english)
                    LanguageType.VIETNAMESE -> stringResource(R.string.vietnamese)
                },
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}