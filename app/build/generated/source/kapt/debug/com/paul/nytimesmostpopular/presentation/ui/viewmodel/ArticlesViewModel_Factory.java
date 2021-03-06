// Generated by Dagger (https://dagger.dev).
package com.paul.nytimesmostpopular.presentation.ui.viewmodel;

import com.paul.nytimesmostpopular.domain.usecases.GetAllArticlesUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import javax.inject.Provider;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ArticlesViewModel_Factory implements Factory<ArticlesViewModel> {
  private final Provider<GetAllArticlesUseCase> articlesUseCaseProvider;

  public ArticlesViewModel_Factory(Provider<GetAllArticlesUseCase> articlesUseCaseProvider) {
    this.articlesUseCaseProvider = articlesUseCaseProvider;
  }

  @Override
  public ArticlesViewModel get() {
    return newInstance(articlesUseCaseProvider.get());
  }

  public static ArticlesViewModel_Factory create(
      Provider<GetAllArticlesUseCase> articlesUseCaseProvider) {
    return new ArticlesViewModel_Factory(articlesUseCaseProvider);
  }

  public static ArticlesViewModel newInstance(GetAllArticlesUseCase articlesUseCase) {
    return new ArticlesViewModel(articlesUseCase);
  }
}
